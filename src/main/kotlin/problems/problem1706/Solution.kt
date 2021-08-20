package problems.problem1706

class Solution {
  fun findBall(grid: Array<IntArray>): IntArray {
    fun recurse(row: Int, column: Int, direction: Direction): Int {
      if (row >= grid.size) {
        return column
      }
      val value = grid[row][column]
      val nextDirection = if (value == 1) Direction.RIGHT else Direction.LEFT
      if (nextDirection == Direction.RIGHT) {
        if (grid[row].size <= column + 1 ||
          grid[row][column] != grid[row][column + 1]) {
          return -1
        }
      }
      else {
        if (column - 1 < 0 || grid[row][column] != grid[row][column - 1]) {
          return -1
        }
      }

      val (nextRow, nextColumn) = if (nextDirection == Direction.RIGHT) Pair(row + 1, column + 1) else Pair(row + 1, column - 1)
      return recurse(nextRow, nextColumn, nextDirection)
    }
    val columns = mutableListOf<Int>()
    for (column in grid[0].indices) {
      val value = grid[0][column]
      columns.add(recurse(0, column, if (value == 1) Direction.RIGHT else Direction.LEFT))
    }
    return columns.toIntArray()
  }
}

enum class Direction {
  RIGHT,
  LEFT
}
fun main(args: Array<String>) {
  println(Solution().findBall(
    arrayOf(intArrayOf(1,1,1,-1,-1),
    intArrayOf(1,1,1,-1,-1),
    intArrayOf(-1,-1,-1,1,1),
    intArrayOf(1,1,1,1,-1),
    intArrayOf(-1,-1,-1,-1,-1)
    )
  ))
}