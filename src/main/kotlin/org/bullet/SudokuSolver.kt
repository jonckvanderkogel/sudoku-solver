package org.bullet

import org.bullet.Action.*
import org.slf4j.LoggerFactory
import java.lang.IllegalStateException

fun main() {
    val sudokuSolver = SudokuSolver(TriangularSudoku())

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

class SudokuSolver(private val sudoku: Sudoku) {
    companion object {
        private val logger = LoggerFactory.getLogger(SudokuSolver::class.java)
    }

    fun solve(): List<Move> {
        return generateMoves(sudoku.generateSetup())
    }

    private tailrec fun generateMoves(moves: List<Move>): List<Move> {
        return if (isFieldComplete(moves)) {
            moves
        } else {
            generateMoves(moves.plusMaybe(findBestGroup(moves)?.firstAvailableCell(moves)?.findAvailableMove(moves)) ?: backTrack(moves))
        }
    }

    private fun isFieldComplete(moves: List<Move>): Boolean {
        val fieldComplete: Boolean = sudoku
            .cellMap
            .values
            .count() == moves.count()

        logger.debug("Field complete: $fieldComplete")
        return fieldComplete
    }

    private fun findBestGroup(moves: List<Move>): Group? {
        return sudoku
            .groupList
            .map { group -> Pair(group, group.countPopulatedCells(moves)) }
            .filter{ pair -> pair.second < Group.GROUP_SIZE }
            .maxByOrNull{ it.second }?.first
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
            backTrack(moves.dropLast(1))
        } else {
            nextMove.impossible.addAll(lastMove.impossible)
            moves.dropLast(1).plus(nextMove)
        }
    }

    private fun processFollows(moves: List<Move>): List<Move> {
        return backTrack(moves.dropLast(1))
    }
}