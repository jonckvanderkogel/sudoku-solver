package org.bullet

data class Group(val id: Int, val cells: List<Cell>) {
    companion object {
        const val GROUP_SIZE = 9
    }

    init {
        cells.forEach { it.groupList.add(this) }
    }

    fun countPopulatedCells(): Int {
        return cells.count { it.hasValue() }
    }

    fun firstAvailableCell(): Cell? {
        return cells.first { cell: Cell -> !cell.hasValue() }
    }

    fun findAvailableValues(): List<Int> {
        return (1..9)
            .filter { i ->
                cells.none { it.value == i }
            }
    }
}