# sudoku-solver
A sudoku solver in Kotlin using tail recursion.

# Background
My son and I were doing puzzles together and we ran into this particularly difficult triangular sudoku puzzle:
![image](https://user-images.githubusercontent.com/13097206/80927138-f51d3200-8d9b-11ea-859d-5107a2867dd1.png)

At first we tried to manually solve it but soon we found out that to solve this you need to maintain a lot of administration of all the things you tried and had to discard. My son lost interest but since I was studying Kotlin at the time I wanted to write a program to solve this one using (tail) recursion as the solution strategy I devised was particularly suited for this.

# Solution strategy
## Setup
The sudoku is as any other except the grouping here is triangular rather than in squares for a regular sudoku. But the basic idea is the same: you have groups of cells, rows and columns each of which can only contain the number 1-9 once. The numbering of the cells is from top to bottom, left to right, starting with the very top cell as index 1, the row below that the left-most cell has index 2, one to the right 3 and so forth. In the picture above you can see the index numbers of the cells at the edges. Please ignore the filled in numbers, these are the remainders of our manual efforts.

Now that the cells are numbered, we now need to put them in groups. In this case every "large" triangle forms a group, every horizontal row forms a group (and a triangle that sticks out to the top or bottom of it is also part of this group), every left-angled vertical column (again with small triangles sticking out to the side of a column are also part of this group) and every right-angled vertical column (again with the sticking out triangles being part of this group) is a group. Every group consists of 9 cells. A cell is therefore part of multiple groups (In a generic sudoku this would translate to every large square forming a group, every row a group and every column a group). This setup is done in the PlayingField class.

## Solving
To solve the sudoku, we first select the "best" group, i.e. the group with the least cells (but at least one) without a value. From this group we select a cell without a value and calculate a value that would be available for this cell. A value that is available is a value that is not present in any of the groups that this cell is part of. If there were multiple values available for this cell, this move would be marked as a "guess". If there is only one value available we mark it as one that logically follows from the moves that were made previously. If at one point we run into a cell for which we have no available values it must mean we made a wrong guess earlier on. That means we have to backtrack through our list of moves, discard the moves for which we only had 1 option and mark the value that we "guessed" as impossible for that cell and try one of the other values available.

As we pop and push from the list of moves, marking which values are impossible for a particular position and making new guesses eventually you'll reach a stable situation where all the cells have a valid value.
