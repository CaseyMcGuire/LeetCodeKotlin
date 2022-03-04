package problems.problem0378

import java.util.*

class Solution {
  fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
    val pq = PriorityQueue<Item>(compareBy({ it.num }))
    for (i in matrix.indices) {
      pq.add(Item(matrix[i][0], i, 0))
    }
    var i = 0
    while (i < k - 1) {
      val curElement = pq.poll()
      val row = curElement.row
      val col = curElement.column
      val nextColumn = col + 1
      if (nextColumn < matrix[row].size) {
        pq.add(Item(matrix[row][nextColumn], row, nextColumn))
      }
      i++
    }

    return pq.poll().num
  }
}

data class Item(val num: Int, val row: Int, val column: Int)