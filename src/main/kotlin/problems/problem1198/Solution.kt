package problems.problem1198

import java.util.*

class Solution {
  fun smallestCommonElement(mat: Array<IntArray>): Int {
    var largest = 0
    val frontPq = PriorityQueue<Coordinate>(compareBy({ mat[it.i][it.j] }))
    for (i in mat.indices) {
      val coordinate = Coordinate(i, 0)
      val value = mat[i][0]
      largest = Math.max(largest, value)
      frontPq.add(coordinate)
    }

    while (true) {
      val top = frontPq.poll()
      val topNum = mat[top.i][top.j]
      if (largest == topNum) {
        return topNum
      }

      val next = top.getNext()
      val outOfBounds = next.j == mat[next.i].size
      if (outOfBounds) {
        break
      }
      val nextNum = mat[next.i][next.j]
      largest = Math.max(largest, nextNum)
      frontPq.add(next)
    }
    return -1
  }

}

data class Coordinate(val i: Int, val j: Int) {
  fun getNext(): Coordinate {
    return Coordinate(i, j + 1)
  }
}