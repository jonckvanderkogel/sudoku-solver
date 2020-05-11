package org.bullet

data class Cell(val id: Int) {
    val groupList = mutableListOf<Group>()

    private fun findAvailableValues(moves: List<Move>): List<Int> {
        return (1..9).filter { possibleValue ->
            moves
                .filter { it.cell == this }
                .flatMap { it.impossible }
                .none { it == possibleValue }
        }
    }

    fun findAvailableMove(moves: List<Move>): Move? {
        val possibilities: List<Int> =
            findAvailableValues(moves)
                .filter { possibility ->
                    groupList.all { it.findAvailableValues(moves).contains(possibility) }
                }

        println("For cell $this we have the possibilities: $possibilities")
        return if (possibilities.size > 1) {
            Move(Action.GUESS, this, possibilities[0])
        } else if (possibilities.size == 1) {
            Move(Action.FOLLOWS, this, possibilities[0])
        } else {
            null
        }
    }
}