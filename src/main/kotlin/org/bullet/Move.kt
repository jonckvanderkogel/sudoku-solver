package org.bullet

data class Move(val action: Action, val cell: Cell, val value: Int) {
    val impossible = mutableListOf<Int>()

    fun addImpossible(impossibleValue: Int) : Unit {
        impossible.add(impossibleValue)
    }
}