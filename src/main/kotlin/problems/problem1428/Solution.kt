package problems.problem1428

class Solution {
  fun leftMostColumnWithOne(binaryMatrix: BinaryMatrix): Int {
    val dimensions = binaryMatrix.dimensions()
    val rows = dimensions[0]
    val columns = dimensions[1]
    var leftMostSoFar = -1
    for (row in 0 until rows) {
      var low = 0
      var high = columns
      while (low <= high) {
        val mid = ((low.toLong() + high.toLong()) / 2L).toInt()
        if (mid >= columns) {
          break
        }
        val element = binaryMatrix.get(row, mid)
        println(element)
        if (element == 0) {
          low = mid + 1
        }
        else if (element == 1) {
          val isFinished = mid == 0 || binaryMatrix.get(row, mid - 1) == 0
          if (isFinished) {
            if (leftMostSoFar == -1 || leftMostSoFar > mid) {
              leftMostSoFar = mid
            }
            break
          }
          high = mid - 1
        }
      }
    }
    return leftMostSoFar
  }
}

interface BinaryMatrix {
  fun get(row:Int, col:Int):Int
  fun dimensions():List<Int>
 }