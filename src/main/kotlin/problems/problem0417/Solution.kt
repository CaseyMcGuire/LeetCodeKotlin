package problems.problem0417

class Solution {
  fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
    val canReachPacific = mutableSetOf<Coordinate>()
    val canReachAtlantic = mutableSetOf<Coordinate>()

    fun recurse(coordinate: Coordinate, ocean: Ocean) {
      val reachSet = if (ocean == Ocean.ATLANTIC) canReachAtlantic else canReachPacific
      if (reachSet.contains(coordinate)) {
        return
      }
      reachSet.add(coordinate)

      val neighbors = coordinate.getNeighbors()
        .filter { it.i in 0 until heights.size && it.j in 0 until heights[it.i].size && heights[it.i][it.j] >= heights[coordinate.i][coordinate.j] }
      neighbors.forEach { recurse(it, ocean) }
    }


    for (i in heights.indices) {
      recurse(Coordinate(i, 0), Ocean.PACIFIC)
      recurse(Coordinate(i, heights[0].size - 1), Ocean.ATLANTIC)
    }

    for (j in heights[0].indices) {
      recurse(Coordinate(0, j), Ocean.PACIFIC)
      recurse(Coordinate(heights.size - 1, j), Ocean.ATLANTIC)
    }

    val canReachBoth = mutableListOf<List<Int>>()

    for (i in heights.indices) {
      for (j in heights[i].indices) {
        val c = Coordinate(i, j)
        if (canReachPacific.contains(c) && canReachAtlantic.contains(c)) {
          canReachBoth.add(c.toList())
        }
      }
    }
    return canReachBoth
  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1)
    )
  }

  fun toList(): List<Int> {
    return listOf(i, j)
  }
}
enum class Ocean {
  PACIFIC,
  ATLANTIC
}