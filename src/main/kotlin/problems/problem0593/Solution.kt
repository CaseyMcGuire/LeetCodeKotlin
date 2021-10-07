package problems.problem0593

class Solution {
  fun validSquare(p1: IntArray, p2: IntArray, p3: IntArray, p4: IntArray): Boolean {
    val points = mutableListOf(p1, p2, p3, p4).map { Point(it[0], it[1]) }
    if (points.toSet().size != 4) {
      return false
    }
    val visited = mutableSetOf<Point>()
    var sideLength: Int? = null
    var hypotenuse: Int? = null
    fun recurse(p: Point, prev: Point?): Boolean {
      if (!visited.add(p)) {
        return true
      }
      val sorted = points.filter { it != p }.sortedWith(compareBy({p.distanceTo(it)}))
      val closest = sorted.take(2)
      if (prev != null && !closest.contains(prev)) {
        return false
      }

      val farthest = sorted[2]
      for (closePoint in closest) {
        if (sideLength == null) {
          sideLength = p.distanceTo(closePoint)
        }
        else if (sideLength != p.distanceTo(closePoint)) {
          return false
        }
      }

      val hypoLength = p.distanceTo(farthest)
      if (hypotenuse == null) {
        hypotenuse = hypoLength
      }
      else if (hypoLength != hypotenuse) {
        return false
      }

      val next = if (prev == null) closest[0] else closest.filter { it != prev }.first()

      return recurse(next, p)
    }
    return recurse(points[0], null)
  }
}

data class Point(val x: Int, val y: Int) {
  fun distanceTo(p: Point): Int {
    return Math.sqrt((Math.pow((this.x - p.x).toDouble(), 2.toDouble()) + Math.pow((this.y - p.y).toDouble(), 2.toDouble()))).toInt()
  }
}
