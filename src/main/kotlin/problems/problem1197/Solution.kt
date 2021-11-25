package problems.problem1197

import java.util.*

class Solution {
  fun minKnightMoves(x: Int, y: Int): Int {
    val goal = Coordinate(x, y)
    var curCoordinate = Coordinate(0, 0)
    var steps = 0
    val queue = LinkedList<CoordinateAndMoves>()
    queue.addFirst(CoordinateAndMoves(curCoordinate, 0))
    val visited = mutableSetOf<Coordinate>()
    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      if (!visited.add(cur.coordinate)) {
        continue
      }
      if (cur.coordinate == goal) {
        return cur.moves
      }
      val curDistance = cur.coordinate.distance(goal)

      var moves = getPotentialMoves(cur.coordinate.i, cur.coordinate.j).filter { !visited.contains(it) }
      if (curDistance >= 5) {
        moves = moves.filter { it.distance(goal) <= curDistance }
      }
      for (move in moves) {
        queue.addLast(CoordinateAndMoves(move, cur.moves + 1))
      }
    }
    return steps
  }

  fun getPotentialMoves(x: Int, y: Int): List<Coordinate> {
    return listOf(
      Coordinate(x + 1, y + 2),
      Coordinate(x + 2, y + 1),
      Coordinate(x + 1, y - 2),
      Coordinate(x + 2, y - 1),
      Coordinate(x - 1, y - 2),
      Coordinate(x - 2, y - 1),
      Coordinate(x - 1, y + 2),
      Coordinate(x - 2, y + 1)
    )
  }
}

data class CoordinateAndMoves(val coordinate: Coordinate, val moves: Int)

data class Coordinate(val i: Int, val j: Int) {
  fun distance(other: Coordinate): Int {
    return Math.abs(this.i - other.i) + Math.abs(this.j - other.j)
  }
}

fun main(args: Array<String>) {
  println(Solution().minKnightMoves(5, 5))
}