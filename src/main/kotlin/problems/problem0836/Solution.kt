package problems.problem0836

class Solution {
  fun isRectangleOverlap(rec1: IntArray, rec2: IntArray): Boolean {
    val firstX = Interval(rec1[0], rec1[2])
    val firstY = Interval(rec1[1], rec1[3])

    val secondX = Interval(rec2[0], rec2[2])
    val secondY = Interval(rec2[1], rec2[3])

    return firstX.intersects(secondX) && firstY.intersects(secondY)
  }
}

data class Interval(val start: Int, val end: Int) {
  fun intersects(other: Interval): Boolean {
    val thisRange = this.start + 1 until this.end
    val otherRange = other.start + 1 until other.end
    return other.start in thisRange || other.end in thisRange || this.start in otherRange || this.end in otherRange
  }
}