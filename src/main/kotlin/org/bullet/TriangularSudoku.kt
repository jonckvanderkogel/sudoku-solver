package org.bullet

class TriangularSudoku: Sudoku {
    override val cellMap: Map<Int, Cell>
    override val groupList: List<Group>

    init {
        cellMap = (1..54).associateWith { i -> Cell(i) }

        // horizontal groups
        val group1 = Group(1, getCellGrouping((1..9).toList()))
        val group2 = Group(2, getCellGrouping((10..18).toList()))
        val group3 = Group(3, getCellGrouping((19..27).toList()))
        val group4 = Group(4, getCellGrouping((28..36).toList()))
        val group5 = Group(5, getCellGrouping((37..45).toList()))
        val group6 = Group(6, getCellGrouping((46..54).toList()))

        // triangle groups
        val group7 = Group(7, getCellGrouping(listOf(1, 2, 3, 4, 10, 11, 12, 13, 14)))
        val group8 = Group(8, getCellGrouping(listOf(5, 6, 7, 8, 9, 15, 16, 17, 24)))
        val group9 = Group(9, getCellGrouping(listOf(19, 20, 21, 22, 23, 28, 29, 30, 37)))
        val group10 = Group(10, getCellGrouping(listOf(18, 25, 26, 27, 32, 33, 34, 35, 36)))
        val group11 = Group(11, getCellGrouping(listOf(31, 38, 39, 40, 46, 47, 48, 49, 50)))
        val group12 = Group(12, getCellGrouping(listOf(41, 42, 43, 44, 45, 51, 52, 53, 54)))

        // forward slash groups
        val group13 = Group(13, getCellGrouping(listOf(28, 19, 20, 21, 10, 11, 2, 3, 1)))
        val group14 = Group(14, getCellGrouping(listOf(37, 29, 30, 22, 23, 12, 13, 4, 5)))
        val group15 = Group(15, getCellGrouping(listOf(46, 47, 38, 39, 31, 14, 15, 6, 7)))
        val group16 = Group(16, getCellGrouping(listOf(48, 49, 40, 41, 24, 16, 17, 8, 9)))
        val group17 = Group(17, getCellGrouping(listOf(50, 51, 42, 43, 32, 33, 25, 26, 18)))
        val group18 = Group(18, getCellGrouping(listOf(54, 52, 53, 44, 45, 34, 35, 36, 27)))

        // back slash groups
        val group19 = Group(19, getCellGrouping(listOf(36, 35, 27, 26, 18, 17, 7, 8, 9)))
        val group20 = Group(20, getCellGrouping(listOf(45, 34, 33, 25, 24, 16, 15, 6, 5)))
        val group21 = Group(21, getCellGrouping(listOf(53, 44, 43, 32, 13, 14, 3, 4, 1)))
        val group22 = Group(22, getCellGrouping(listOf(54, 52, 51, 42, 41, 23, 11, 12, 2)))
        val group23 = Group(23, getCellGrouping(listOf(49, 50, 39, 40, 30, 31, 21, 22, 10)))
        val group24 = Group(24, getCellGrouping(listOf(46, 47, 48, 37, 38, 28, 29, 19, 20)))
        groupList = listOf(
            group1,
            group2,
            group3,
            group4,
            group5,
            group6,
            group7,
            group8,
            group9,
            group10,
            group11,
            group12,
            group13,
            group14,
            group15,
            group16,
            group17,
            group18,
            group19,
            group20,
            group21,
            group22,
            group23,
            group24
        )
    }

    override fun generateSetup(): List<Move> {
        val setupList: MutableList<Move> = ArrayList()
        setupList.add(generateSetupMove(1, 5))
        setupList.add(generateSetupMove(4, 9))
        setupList.add(generateSetupMove(7, 2))
        setupList.add(generateSetupMove(8, 3))
        setupList.add(generateSetupMove(10, 3))
        setupList.add(generateSetupMove(12, 6))
        setupList.add(generateSetupMove(16, 5))
        setupList.add(generateSetupMove(19, 6))
        setupList.add(generateSetupMove(24, 4))
        setupList.add(generateSetupMove(26, 7))
        setupList.add(generateSetupMove(28, 1))
        setupList.add(generateSetupMove(29, 8))
        setupList.add(generateSetupMove(35, 6))
        setupList.add(generateSetupMove(37, 4))
        setupList.add(generateSetupMove(38, 5))
        setupList.add(generateSetupMove(39, 8))
        setupList.add(generateSetupMove(42, 9))
        setupList.add(generateSetupMove(46, 3))
        setupList.add(generateSetupMove(50, 1))
        setupList.add(generateSetupMove(52, 2))
        return setupList
    }
}