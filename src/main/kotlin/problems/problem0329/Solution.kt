package problems.problem0329

typealias Coordinate = Pair<Int, Int>

class Solution {
  fun longestIncreasingPath(matrix: Array<IntArray>): Int {
    val beginningNodes = calculateBeginningNodes(matrix)
    val longestPathAtCoordinate = mutableMapOf<Coordinate, Int>()

    fun isTerminalCoordinate(coordinate: Coordinate): Boolean {
      val neighbors = getNeighbors(coordinate, matrix)
      return neighbors.all { matrix[it.first][it.second] <= matrix[coordinate.first][coordinate.second] }
    }

    fun longestPathForCoordinate(currentCoordinate: Coordinate): Int {
      val longestPathSeen = longestPathAtCoordinate[currentCoordinate]
      return when {
        longestPathSeen != null -> {
          longestPathSeen
        }
        isTerminalCoordinate(currentCoordinate) -> {
          1
        }
        else -> {
          val neighbors = getNeighbors(currentCoordinate, matrix)
            .filter { matrix[it.first][it.second] > matrix[currentCoordinate.first][currentCoordinate.second] }
          val longestPath = neighbors.map { longestPathForCoordinate(it) }.max() ?: 0
          val currentPathLength = longestPath + 1
          longestPathAtCoordinate[currentCoordinate] = currentPathLength
          currentPathLength
        }
      }
    }

    return beginningNodes.map {
      longestPathForCoordinate(it)
    }.max() ?: 1
  }

  private fun getNeighbors(coordinate: Coordinate, matrix: Array<IntArray>): List<Coordinate> =
    listOf(
      Coordinate(coordinate.first + 1, coordinate.second),
      Coordinate(coordinate.first - 1, coordinate.second) ,
      Coordinate(coordinate.first, coordinate.second + 1),
      Coordinate(coordinate.first, coordinate.second - 1)
    ).filter { !isOutsideBounds(it, matrix) }

  private fun isOutsideBounds(coordinate: Coordinate, matrix: Array<IntArray>): Boolean =
    coordinate.first < 0 || coordinate.first >= matrix.size || coordinate.second < 0 || coordinate.second >= matrix[coordinate.first].size

  private fun calculateBeginningNodes(matrix: Array<IntArray>): Collection<Coordinate> {
    val beginningNodes = mutableSetOf<Coordinate>()
    for (i in matrix.indices) {
      for (j in matrix[i].indices) {
        val value = matrix[i][j]
        val neighbors = getNeighbors(Coordinate(i,j), matrix)
        val isBeginning = neighbors.all {
          matrix[it.first][it.second] >= value
        }
        if (isBeginning) {
          beginningNodes.add(Coordinate(i, j))
        }
      }
    }
    return beginningNodes
  }
}

fun main(args: Array<String>) {
  println(Solution().longestIncreasingPath(
    arrayOf(
      intArrayOf(7,7,5),
      intArrayOf(2,4,6),
      intArrayOf(8,2,0)
    )
  ))
}