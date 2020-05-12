package org.bullet

/*
 * According to https://gizmodo.com/can-you-solve-the-10-hardest-logic-puzzles-ever-created-1064112665
 * The below setup is the hardest sudoku ever created
 */
class HardestSudoku: TraditionalSudoku() {
    override fun generateSetup(): List<Move> {
        val setupList: MutableList<Move> = ArrayList()
        setupList.add(generateSetupMove(1, 8))
        setupList.add(generateSetupMove(12, 3))
        setupList.add(generateSetupMove(13, 6))
        setupList.add(generateSetupMove(20, 7))
        setupList.add(generateSetupMove(23, 9))
        setupList.add(generateSetupMove(25, 2))
        setupList.add(generateSetupMove(29, 5))
        setupList.add(generateSetupMove(33, 7))
        setupList.add(generateSetupMove(41, 4))
        setupList.add(generateSetupMove(42, 5))
        setupList.add(generateSetupMove(43, 7))
        setupList.add(generateSetupMove(49, 1))
        setupList.add(generateSetupMove(53, 3))
        setupList.add(generateSetupMove(57, 1))
        setupList.add(generateSetupMove(62, 6))
        setupList.add(generateSetupMove(63, 8))
        setupList.add(generateSetupMove(66, 8))
        setupList.add(generateSetupMove(67, 5))
        setupList.add(generateSetupMove(71, 1))
        setupList.add(generateSetupMove(74, 9))
        setupList.add(generateSetupMove(79, 4))

        return setupList
    }
}