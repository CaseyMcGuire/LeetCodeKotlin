package problems.problem2352

class Solution {
  fun equalPairs(grid: Array<IntArray>): Int {
    if (grid.isEmpty()) {
      return 0
    }
    if (grid[0].isEmpty()) {
      return 0
    }

    val rowToIndex = mutableMapOf<List<Int>, MutableSet<Int>>()
    for (i in grid.indices) {
      val row = grid[i].toList()
      val set = rowToIndex[row] ?: mutableSetOf<Int>()
      set.add(i)
      rowToIndex[row] = set
    }

    var matchingPairs = 0
    for (j in grid[0].indices) {
      val col = mutableListOf<Int>()
      for (i in grid.indices) {
        col.add(grid[i][j])
      }
      val matchingRows = rowToIndex[col] ?: mutableSetOf<Int>()
      matchingPairs += matchingRows.size
    }
    return matchingPairs
  }
}