package org.bullet

import org.bullet.Action.*
import java.lang.IllegalStateException

fun main() {
    val playingField = PlayingField()
    val sudokuSolver = SudokuSolver(playingField)

    val moves = sudokuSolver.generateMoves(playingField.generateSetup())

    moves.sortedBy { it.cell.id }.forEach { println(it) }
}

class SudokuSolver(val playingField: PlayingField) {

    tailrec fun generateMoves(moves: List<Move>): List<Move> {
        if (isFieldComplete()) {
            return moves
        } else {
            val move = findBestGroup()?.firstAvailableCell()?.findAvailableMove()

            if (move == null) {
                return backTrack(moves)
            } else {
                return generateMoves(moves.plus(move))
            }
        }
    }

    private fun isFieldComplete(): Boolean {
        val fieldComplete: Boolean = playingField
            .cellMap
            .values
            .none { cell -> cell.value == 0 }

        println("Field complete: $fieldComplete")
        return fieldComplete
    }

    private fun findBestGroup(): Group? {
        return playingField
            .groupList
            .filter{ group -> group.countPopulatedCells() < Group.GROUP_SIZE }
            .maxBy{ it.countPopulatedCells() }
    }

    private fun backTrack(moves: List<Move>): List<Move> {
        val lastMove = moves.last()
        if (lastMove.action == GUESS) {
            return processGuess(moves)
        } else if (lastMove.action == FOLLOWS) {
            return processFollows(moves)
        } else {
            throw IllegalStateException("Impossible sudoku. Did you set up the playing field correctly?")
        }
    }

    private fun processGuess(moves: List<Move>): List<Move> {
        val lastMove = moves.last()
        lastMove.cell.addImpossible(lastMove.value)
        val nextMove = lastMove.cell.findAvailableMove()
        if (nextMove == null) {
            lastMove.cell.clearCell()
            return backTrack(moves.subList(0, moves.count() - 1))
        } else {
            return generateMoves(moves.subList(0, moves.count() - 1).plus(nextMove))
        }
    }

    private fun processFollows(moves: List<Move>): List<Move> {
        val lastMove = moves.last()
        lastMove.cell.clearCell()
        return backTrack(moves.subList(0, moves.count() - 1))
    }
}