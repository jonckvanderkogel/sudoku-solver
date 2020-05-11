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

class SudokuSolver(private val playingField: PlayingField) {

    fun solve(): List<Move> {
        return generateMoves(playingField.generateSetup())
    }

    private tailrec fun generateMoves(moves: List<Move>): List<Move> {
        return if (isFieldComplete(moves)) {
            moves
        } else {
            generateMoves(moves.plusMaybe(findBestGroup(moves)?.firstAvailableCell(moves)?.findAvailableMove(moves)) ?: backTrack(moves))
        }
    }

    private fun isFieldComplete(moves: List<Move>): Boolean {
        val fieldComplete: Boolean = playingField
            .cellMap
            .values
            .count() == moves.count()

        println("Field complete: $fieldComplete")
        return fieldComplete
    }

    private fun findBestGroup(moves: List<Move>): Group? {
        return playingField
            .groupList
            .filter{ group -> group.countPopulatedCells(moves) < Group.GROUP_SIZE }
            .maxBy{ it.countPopulatedCells(moves) }
    }

    private fun backTrack(moves: List<Move>): List<Move> {
        val lastMove = moves.last()
        return when (lastMove.action) {
            GUESS -> processGuess(moves)
            FOLLOWS -> processFollows(moves)
            GIVEN -> throw IllegalStateException("Impossible sudoku. Did you set up the playing field correctly?")
        }
    }

    private fun processGuess(moves: List<Move>): List<Move> {
        val lastMove = moves.last()
        lastMove.addImpossible(lastMove.value)
        val nextMove = lastMove.cell.findAvailableMove(moves)
        return if (nextMove == null) {
            backTrack(moves.subList(0, moves.count() - 1))
        } else {
            nextMove.impossible.addAll(lastMove.impossible)
            moves.subList(0, moves.count() - 1).plus(nextMove)
        }
    }

    private fun processFollows(moves: List<Move>): List<Move> {
        return backTrack(moves.subList(0, moves.count() - 1))
    }
}