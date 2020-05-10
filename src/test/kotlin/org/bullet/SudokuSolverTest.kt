package org.bullet

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SudokuSolverTest {

    @Test
    fun testCorrectMovesGenerated() {
        val sudokuSolver = SudokuSolver(PlayingField())

        val moves = sudokuSolver.solve().sortedBy { it.cell.id }

        assertEquals(5, moves[0].value)
        assertEquals(7, moves[1].value)
        assertEquals(4, moves[2].value)
        assertEquals(9, moves[3].value)
        assertEquals(1, moves[4].value)
        assertEquals(6, moves[5].value)
        assertEquals(2, moves[6].value)
        assertEquals(3, moves[7].value)
        assertEquals(8, moves[8].value)
        assertEquals(3, moves[9].value)
        assertEquals(8, moves[10].value)
        assertEquals(6, moves[11].value)
        assertEquals(2, moves[12].value)
        assertEquals(1, moves[13].value)
        assertEquals(7, moves[14].value)
        assertEquals(5, moves[15].value)
        assertEquals(9, moves[16].value)
        assertEquals(4, moves[17].value)
        assertEquals(6, moves[18].value)
        assertEquals(2, moves[19].value)
        assertEquals(9, moves[20].value)
        assertEquals(5, moves[21].value)
        assertEquals(3, moves[22].value)
        assertEquals(4, moves[23].value)
        assertEquals(8, moves[24].value)
        assertEquals(7, moves[25].value)
        assertEquals(1, moves[26].value)
        assertEquals(1, moves[27].value)
        assertEquals(8, moves[28].value)
        assertEquals(7, moves[29].value)
        assertEquals(4, moves[30].value)
        assertEquals(3, moves[31].value)
        assertEquals(2, moves[32].value)
        assertEquals(9, moves[33].value)
        assertEquals(6, moves[34].value)
        assertEquals(5, moves[35].value)
        assertEquals(4, moves[36].value)
        assertEquals(5, moves[37].value)
        assertEquals(8, moves[38].value)
        assertEquals(2, moves[39].value)
        assertEquals(1, moves[40].value)
        assertEquals(9, moves[41].value)
        assertEquals(6, moves[42].value)
        assertEquals(7, moves[43].value)
        assertEquals(3, moves[44].value)
        assertEquals(3, moves[45].value)
        assertEquals(9, moves[46].value)
        assertEquals(7, moves[47].value)
        assertEquals(6, moves[48].value)
        assertEquals(1, moves[49].value)
        assertEquals(5, moves[50].value)
        assertEquals(2, moves[51].value)
        assertEquals(8, moves[52].value)
        assertEquals(4, moves[53].value)
    }
}
