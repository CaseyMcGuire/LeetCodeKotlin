package problems.problem0452

class Solution {
  fun findMinArrowShots(points: Array<IntArray>): Int {
    val sortedPoints = points.sortedWith(compareBy({ it[0] }, { it[1] }))
    var minEnd = -1
    var numArrows = 0
    for (point in sortedPoints) {
      if (minEnd == -1) {
        minEnd = point[1]
      }
      else if (minEnd < point[0]) {
        numArrows++
        minEnd = point[1]
      }
      else {
        minEnd = Math.min(minEnd, point[1])
      }
    }
    numArrows++
    return numArrows
  }
}