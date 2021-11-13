package problems.problem0531

class Solution {
  fun findLonelyPixel(picture: Array<CharArray>): Int {
    if (picture.isEmpty() || picture[0].isEmpty()) {
      return -1
    }
    val lonelyRowPixels = mutableSetOf<Coordinate>()
    val height = picture.size
    val width = picture[0].size
    var numLonelyPixels = 0
    for (row in 0 until height) {
      var blackPixelRow: Coordinate? = null
      for (column in 0 until width) {
        if (picture[row][column] == 'B') {
          if (blackPixelRow == null) {
            blackPixelRow = Coordinate(row, column)
          }
          else {
            blackPixelRow = null
            break
          }
        }
      }

      if (blackPixelRow == null) {
        continue
      }

      var foundPixel = false
      for (i in 0 until height) {
        if (picture[i][blackPixelRow.j] == 'B') {
          if (foundPixel == false) {
            foundPixel = true
          }
          else {
            foundPixel = false
            break
          }
        }
      }
      if (foundPixel) {
        numLonelyPixels++
      }

    }
    return numLonelyPixels
  }
}

data class Coordinate(val i: Int, val j: Int)