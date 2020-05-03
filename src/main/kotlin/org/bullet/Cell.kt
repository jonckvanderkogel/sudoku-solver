package org.bullet

data class Cell(val id: Int) {
    val groupList = mutableListOf<Group>()
    val impossible = mutableListOf<Int>()
    var value = 0

    fun clearCell(): Unit {
        impossible.clear()
        value = 0
    }

    fun addImpossible(impossibleValue: Int) : Unit {
        impossible.add(impossibleValue)
        value = 0
    }

    fun hasValue(): Boolean {
        return value != 0
    }

    private fun findAvailableValues(): List<Int> {
        return (1..9).filter { !impossible.contains(it) }
    }

    fun findAvailableMove(): Move? {
        val possibilities: List<Int> =
            findAvailableValues()
            .filter{ possibility ->
                groupList
                    .filter{ group -> group.findAvailableValues().contains(possibility) }
                    .count() == groupList.size
            }

        println("For cell $this we have the possibilities: $possibilities")
        if (possibilities.size > 1) {
            val newValue = possibilities[0]
            value = newValue
            return Move(Action.GUESS, this, newValue)
        } else if (possibilities.size == 1) {
            val newValue = possibilities[0]
            value = newValue
            return Move(Action.FOLLOWS, this, newValue)
        } else {
            return null
        }
    }
}