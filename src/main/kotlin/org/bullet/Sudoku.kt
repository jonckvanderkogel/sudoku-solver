package org.bullet

interface Sudoku {
    val cellMap: Map<Int, Cell>
    val groupList: List<Group>

    fun generateSetup(): List<Move>

    fun getCellGrouping(ids: List<Int>): List<Cell> {
        require(ids.size == Group.GROUP_SIZE) { "Grouping should always be ${Group.GROUP_SIZE} long" }
        return ids.map { i -> cellMap[i]!! }
    }

    fun generateSetupMove(cellId: Int, value: Int): Move {
        return Move(Action.GIVEN, cellMap[cellId]!!, value)
    }
}