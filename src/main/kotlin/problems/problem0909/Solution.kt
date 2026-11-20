package problems.problem0909

class Solution {
  fun snakesAndLadders(board: Array<IntArray>): Int {
    val labelToCoordinates = getLabelToCoordinates(board.size)
    val n = board.size
    val total = n * n
    val queue = ArrayDeque<Node>()
    queue.addFirst(Node(1, 0))
    val visitedOrPending = mutableSetOf<Int>(1)

    while (queue.isNotEmpty()) {
      val next = queue.removeFirst()
      for (i in (next.label + 1)..Math.min(next.label + 6, total)) {
        if (!visitedOrPending.contains(i)) {
          visitedOrPending.add(i)
          val coordinate = labelToCoordinates[i]!!
          val destination = board[coordinate.i][coordinate.j]
          val nextLabel = if (destination != -1) destination else i
          if (nextLabel == total) {
            return next.numRolls + 1
          }
          queue.addLast(Node(nextLabel, next.numRolls + 1))
        }
      }
    }
    return -1
  }

  private fun getLabelToCoordinates(n: Int): Map<Int, Coordinate> {
    val labelToCoordinates = mutableMapOf<Int, Coordinate>()

    var i = 1
    var front = true
    for (row in n - 1 downTo 0) {
      val range = if (front) 0 until n else n - 1 downTo 0
      for (col in range) {
        labelToCoordinates[i] = Coordinate(row, col)
        i++
      }
      front = !front
    }
    return labelToCoordinates
  }

  data class Coordinate(val i: Int, val j: Int)
  data class Node(val label: Int, val numRolls: Int)
}
