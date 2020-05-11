package org.bullet

import kotlin.math.floor

class TraditionalSudoku: Sudoku {
    override val cellMap: Map<Int, Cell> = (1..81).associateWith { i -> Cell(i) }
    override val groupList: List<Group> = generateGroupings()

    private fun generateGroupings(): List<Group> {
        val horizontalGroups = (1..9).map { Group(it, getCellGrouping((((it - 1) * 8) + it..9*it).toList())) }
        val verticalGroups = (10..18).map { Group(it, getCellGrouping((it-9..it+63 step 9).toList())) }
        val blockGroups = (1..9).map {
                ((it - 1) * 3) + 1 + floor((((it - 1)/3).toDouble())).toInt() * 18
        }.map {
            (it..it+2).toList().plus((it+9..it+11).toList()).plus((it+18..it+20).toList())
        }.mapIndexed { index, list -> Group(index + 19, getCellGrouping(list)) }

        return horizontalGroups.plus(verticalGroups).plus(blockGroups)
    }

    override fun generateSetup(): List<Move> {
        val setupList: MutableList<Move> = ArrayList()
        setupList.add(generateSetupMove(6, 7))
        setupList.add(generateSetupMove(8, 1))
        setupList.add(generateSetupMove(12, 8))
        setupList.add(generateSetupMove(13, 2))
        setupList.add(generateSetupMove(14, 1))
        setupList.add(generateSetupMove(15, 5))
        setupList.add(generateSetupMove(17, 7))
        setupList.add(generateSetupMove(18, 9))
        setupList.add(generateSetupMove(20, 4))
        setupList.add(generateSetupMove(21, 7))
        setupList.add(generateSetupMove(22, 9))
        setupList.add(generateSetupMove(23, 3))
        setupList.add(generateSetupMove(24, 8))
        setupList.add(generateSetupMove(25, 2))
        setupList.add(generateSetupMove(26, 5))
        setupList.add(generateSetupMove(28, 8))
        setupList.add(generateSetupMove(29, 7))
        setupList.add(generateSetupMove(30, 6))
        setupList.add(generateSetupMove(32, 5))
        setupList.add(generateSetupMove(34, 3))
        setupList.add(generateSetupMove(35, 9))
        setupList.add(generateSetupMove(36, 2))
        setupList.add(generateSetupMove(41, 6))
        setupList.add(generateSetupMove(43, 5))
        setupList.add(generateSetupMove(44, 8))
        setupList.add(generateSetupMove(46, 9))
        setupList.add(generateSetupMove(49, 8))
        setupList.add(generateSetupMove(51, 3))
        setupList.add(generateSetupMove(52, 7))
        setupList.add(generateSetupMove(54, 1))
        setupList.add(generateSetupMove(56, 2))
        setupList.add(generateSetupMove(57, 9))
        setupList.add(generateSetupMove(58, 5))
        setupList.add(generateSetupMove(65, 6))
        setupList.add(generateSetupMove(67, 3))
        setupList.add(generateSetupMove(72, 5))
        setupList.add(generateSetupMove(73, 5))
        setupList.add(generateSetupMove(74, 8))
        setupList.add(generateSetupMove(78, 4))
        setupList.add(generateSetupMove(79, 6))
        setupList.add(generateSetupMove(80, 2))
        setupList.add(generateSetupMove(81, 7))
        return setupList
    }
}