package org.bullet

import org.bullet.Action.*
import java.lang.IllegalStateException

fun main() {
    val sudokuSolver = SudokuSolver(PlayingField())

    val moves = sudokuSolver.solve()

    moves.sortedBy { it.cell.id }.forEach { println(it) }
}

fun <T> List<T>.plusMaybe(move: T?) : List<T>? {
    return if (move == null) {
        null
    } else {
        this.plus(move)
    }
}

class SudokuSolver(val playingField: PlayingField) {

    fun solve(): List<Move> {
        return generateMoves(playingField.generateSetup())
    }

    private tailrec fun generateMoves(moves: List<Move>): List<Move> {
        if (isFieldComplete()) {
            return moves
        } else {
            return generateMoves(moves.plusMaybe(findBestGroup()?.firstAvailableCell()?.findAvailableMove()) ?: backTrack(moves))
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
            return moves.subList(0, moves.count() - 1).plus(nextMove)
        }
    }

    private fun processFollows(moves: List<Move>): List<Move> {
        val lastMove = moves.last()
        lastMove.cell.clearCell()
        return backTrack(moves.subList(0, moves.count() - 1))
    }
}