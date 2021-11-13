package problems.problem0361

class Solution {
  fun maxKilledEnemies(grid: Array<CharArray>): Int {
    val verticalSum = mutableMapOf<Coordinate, Int>()
    val horizontalSum = mutableMapOf<Coordinate, Int>()
    var maxSoFar = 0
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        if (grid[i][j] == 'W') {
          continue
        }
        val coordinate = Coordinate(i, j)
        var k = i
        var coords = mutableSetOf<Coordinate>()
        var curVerticalSum = 0
        val cachedVerticalSum = verticalSum[coordinate]
        if (cachedVerticalSum == null)  {
          while (k < grid.size) {

            if (grid[k][j] == 'W') {
              break
            }
            else if (grid[k][j] == 'E') {
              curVerticalSum++
            }
            coords.add(Coordinate(k, j))
            k++
          }
        }
        else {
          curVerticalSum = cachedVerticalSum
        }
        coords.forEach { verticalSum[it] = curVerticalSum }
        coords = mutableSetOf<Coordinate>()

        var curHorizontalSum = 0
        val cachedHorizontalSum = horizontalSum[coordinate]
        var l = j
        if (cachedHorizontalSum == null) {
          while (l < grid[i].size) {
            if (grid[i][l] == 'W') {
              break
            }
            else if (grid[i][l] == 'E') {
              curHorizontalSum++
            }
            coords.add(Coordinate(i, l))
            l++
          }
        }
        else {
          curHorizontalSum = cachedHorizontalSum
        }

        coords.forEach { horizontalSum[it] = curHorizontalSum }

        val total = curHorizontalSum + curVerticalSum
        if (grid[i][j] == '0' && total > maxSoFar) {
          maxSoFar = total
        }
      }
    }
    return maxSoFar
  }
}

data class Coordinate(val i: Int, val j: Int)