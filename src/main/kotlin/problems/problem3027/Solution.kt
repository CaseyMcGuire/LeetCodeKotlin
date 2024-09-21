package problems.problem3027

class Solution {
  fun numberOfPairs(points: Array<IntArray>): Int {
    val pointList = points.map { Point(it[0], it[1]) }
      .sortedWith(compareBy({ it.x }, { -it.y }))

    var num = 0
    for (i in pointList.indices) {
      var minY: Int? = null
      val alice = pointList[i]
      for (j in i + 1 until pointList.size) {
        val bob = pointList[j]
        if (bob.y <= alice.y) {
          if (minY == null) {
            minY = bob.y
            num++
          }
          else if (minY < bob.y) {
            num++
            minY = Math.max(minY, bob.y)
          }

        }
      }
    }
    return num
  }
}

data class Point(val x: Int, val y: Int)