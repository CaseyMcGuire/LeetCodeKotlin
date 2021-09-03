package problems.problem0986

class Solution {
  fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
    val intersections = mutableListOf<Interval>()
    val first = firstList.map {Interval(it[0], it[1])}
    val second = secondList.map {Interval(it[0], it[1])}

    var firstIndex = 0
    var secondIndex = 0
    while (firstIndex < first.size && secondIndex < second.size) {
      val currentFirst = first[firstIndex]
      val currentSecond = second[secondIndex]
      val intersection = currentFirst.getIntersection(currentSecond)
      if (intersection != null) {
        intersections.add(intersection)
      }
      if (currentFirst.end > currentSecond.end) {
        secondIndex++
      }
      else {
        firstIndex++
      }
    }
    return intersections.map {intArrayOf(it.start, it.end)}.toTypedArray()

  }
}

data class Interval(val start: Int, val end: Int) {

  fun getIntersection(other: Interval): Interval? {
    val (lower, higher) = if (this.start < other.start) Pair(this, other) else Pair(other, this)
    var startInterval = if (higher.start in lower.start..lower.end) higher.start else return null
    var endInterval = if (higher.end > lower.end) lower.end else higher.end
    return Interval(startInterval, endInterval)
  }
}
