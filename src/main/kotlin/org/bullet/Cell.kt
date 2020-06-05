package org.bullet

import org.slf4j.LoggerFactory

data class Cell(val id: Int) {
    companion object {
        private val logger = LoggerFactory.getLogger(Cell::class.java)
    }

    val groupList = mutableListOf<Group>()

    private fun findAvailableValues(moves: List<Move>): List<Int> {
        return (1..9).minus(
            moves
                .filter { it.cell == this }
                .flatMap { it.impossible }
                .toSet()
        )
    }

    fun findAvailableMove(moves: List<Move>): Move? {

        val possibilities = findAvailableValues(moves).intersect(
            groupList.fold(
                (1..9).toSet(),
                { sharedValues, group -> group.findAvailableValues(moves).intersect(sharedValues) }
            )
        ).toList()

        logger.debug("For cell $this we have the possibilities: $possibilities")
        return if (possibilities.size > 1) {
            Move(Action.GUESS, this, possibilities[0])
        } else if (possibilities.size == 1) {
            Move(Action.FOLLOWS, this, possibilities[0])
        } else {
            null
        }
    }
}