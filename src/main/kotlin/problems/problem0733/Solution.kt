package problems.problem0733

class Solution {
  fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {

    val startingCoordinate = Coordinate(sr, sc)
    val startingColor = image[sr][sc]
    val visited = mutableSetOf<Coordinate>()

    fun traverse(c: Coordinate) {
      if (!image.containsCoordinate(c) || visited.contains(c)) {
        return
      }

      visited.add(c)
      if (image[c.i][c.j] == startingColor) {
        image[c.i][c.j] = color
      }
      else {
        return
      }

      for (neighbor in c.getNeighbors()) {
        traverse(neighbor)
      }
    }

    traverse(startingCoordinate)

    return image
  }

  private fun Array<IntArray>.containsCoordinate(c: Coordinate): Boolean {
    return c.i in this.indices && c.j in this[c.i].indices
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
}