package problems.problem1762

class Solution {
  fun findBuildings(heights: IntArray): IntArray {
    var tallestSoFar = -1
    val buildingsWithOceanView = mutableListOf<Int>()
    for (i in heights.indices.reversed()) {
      if (heights[i] > tallestSoFar) {
        buildingsWithOceanView.add(i)
      }
      tallestSoFar = Math.max(tallestSoFar, heights[i])
    }
    return buildingsWithOceanView.sorted().toIntArray()
  }
}