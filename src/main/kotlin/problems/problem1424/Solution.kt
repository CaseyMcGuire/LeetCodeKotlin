package problems.problem1424

import java.util.LinkedList

class Solution {
  fun findDiagonalOrder(nums: List<List<Int>>): IntArray {
    val startNodeToNums = mutableMapOf<Coordinate, LinkedList<Int>>()

    for (i in nums.indices) {
      var curCoordinate = Coordinate(i, 0)
      for (j in nums[i].indices) {
        val numsForNode = startNodeToNums.getOrPut(curCoordinate) { LinkedList<Int>() }
        numsForNode.addFirst(nums[i][j])

        if (curCoordinate.i == nums.size - 1) {
          curCoordinate = curCoordinate.right()
        }
        else {
          curCoordinate = curCoordinate.down()
        }
      }
    }
    val nums = startNodeToNums.entries.sortedWith(compareBy({ it.key.i }, { it.key.j })).flatMap { it.value }
    return nums.toIntArray()
  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun down(): Coordinate {
    return Coordinate(i + 1, j)
  }

  fun right(): Coordinate {
    return Coordinate(i, j + 1)
  }

}