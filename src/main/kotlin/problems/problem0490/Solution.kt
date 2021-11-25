package problems.problem0490

class Solution {
  fun hasPath(maze: Array<IntArray>, start: IntArray, destination: IntArray): Boolean {
    val destinationCoordinate = Coordinate(destination[0], destination[1])
    val startCoordinate = Coordinate(start[0], start[1])
    val visited = mutableSetOf<Coordinate>()
    fun dfs(curCoordinate: Coordinate): Boolean {
      if (curCoordinate == destinationCoordinate) {
        return true
      }

      val nextPositions = getNextPositions(curCoordinate, maze).filter { !visited.contains(it) }
      visited.add(curCoordinate)
      for (position in nextPositions) {
        if(dfs(position)) {
          return true
        }

      }
      return false
    }
    return dfs(startCoordinate)
  }

  private fun getNextPositions(coordinate: Coordinate, maze: Array<IntArray>): List<Coordinate> {
    val positions = mutableListOf<Coordinate>()
    for (i in -1..1) {
      if (i == 0) {
        continue
      }
      var curCoordinate = Coordinate(coordinate.i + i, coordinate.j)
      if (!maze.isValid(curCoordinate)) {
        continue
      }
      while (true) {
        val next = Coordinate(curCoordinate.i + i, curCoordinate.j)
        if (!maze.isValid(next)) {
          break
        }
        curCoordinate = next
      }
      positions.add(curCoordinate)
    }
    for (j in -1..1) {
      if (j == 0) {
        continue
      }
      var curCoordinate = Coordinate(coordinate.i, coordinate.j + j)
      if (!maze.isValid(curCoordinate)) {
        continue
      }
      while (true) {
        val next = Coordinate(curCoordinate.i, curCoordinate.j + j)
        if (!maze.isValid(next)) {
          break
        }
        curCoordinate = next
      }
      positions.add(curCoordinate)
    }
    return positions
  }

  private fun Array<IntArray>.isValid(coordinate: Coordinate): Boolean {
    val rowRange = 0 until this.size
    val columnRange = 0 until this[0].size
    return coordinate.i in rowRange && coordinate.j in columnRange && this[coordinate.i][coordinate.j] == 0
  }
}

data class Coordinate(val i: Int, val j: Int)