package org.bullet

data class Group(val id: Int, val cells: List<Cell>) {
    companion object {
        const val GROUP_SIZE = 9
    }

    init {
        cells.forEach { it.groupList.add(this) }
    }

    fun countPopulatedCells(moves: List<Move>): Int {
        return moves.count { cells.contains(it.cell) }
    }

    fun firstAvailableCell(moves: List<Move>): Cell? {
        return cells.first { cell -> moves.none { it.cell == cell } }
    }

    fun findAvailableValues(moves: List<Move>): List<Int> {
        return (1..9).minus(
            moves
                .filter { cells.contains(it.cell) }
                .map { it.value }
                .toSet()
        )
    }
}