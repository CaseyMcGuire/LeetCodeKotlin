package problems.problem0531

class Solution {
  fun findLonelyPixel(picture: Array<CharArray>): Int {
    val colToNumBlackPixels = mutableMapOf<Int, Int>()
    val singlePixelRowPoints = mutableSetOf<Point>()
    for (i in picture.indices) {
      val blackPoints = mutableSetOf<Point>()
      for (j in picture[i].indices) {
        if (picture[i][j] == 'B') {
          blackPoints.add(Point(i, j))
          colToNumBlackPixels.merge(j, 1) { cur, acc -> cur + acc }
        }
      }

      if (blackPoints.size == 1) {
        singlePixelRowPoints.addAll(blackPoints)
      }
    }

    var numLonelyPixels = 0
    for (point in singlePixelRowPoints) {
      val numPixelsInColumn = colToNumBlackPixels[point.j]!!
      if (numPixelsInColumn == 1) {
        numLonelyPixels++
      }
    }
    return numLonelyPixels
  }

  data class Point(val i: Int, val j: Int)
}