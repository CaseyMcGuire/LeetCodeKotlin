package problems.problem0286

import java.util.*

class Solution {
  fun wallsAndGates(rooms: Array<IntArray>): Unit {

    val gates = mutableListOf<Coordinate>()
    // find gates
    for (i in rooms.indices) {
      for (j in rooms[i].indices) {
        if (rooms[i][j] == 0) {
          gates.add(Coordinate(i, j))
        }
      }
    }

    val queue = LinkedList<GateAndCoordinate>()
    for (gate in gates) {
      queue.add(GateAndCoordinate(gate, 0))
    }
    val rowRange = 0 until rooms.size
    val columnRange = 0 until rooms[0].size
    val valueRange = 1 until Integer.MAX_VALUE
    val visited = mutableSetOf<Coordinate>()
    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()
      val coordinate = current.coordinate
      if (!visited.add(coordinate)) {
        continue
      }
      val value = rooms[coordinate.i][coordinate.j]
      if (value == -1 || value in valueRange) {
        continue
      }
      if (value == Integer.MAX_VALUE) {
        rooms[coordinate.i][coordinate.j] = current.distance
      }

      val neighbors = coordinate.getNeighbors().filter { it.i in rowRange && it.j in columnRange && !visited.contains(it) }
      neighbors.forEach { queue.addLast(GateAndCoordinate(it, current.distance + 1)) }
    }
  }
}

data class GateAndCoordinate(val coordinate: Coordinate, val distance: Int)
data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1)
    )
  }
}