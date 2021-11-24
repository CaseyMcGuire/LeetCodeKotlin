package problems.problem0635

import java.util.*

class LogSystem() {

  private val map = TreeMap<String, Int>()

  companion object {
    val granularities = listOf(
      "Year",
      "Month",
      "Day",
      "Hour",
      "Minute",
      "Second"
    )
  }

  fun put(id: Int, timestamp: String) {
    map[timestamp] = id
  }

  fun retrieve(start: String, end: String, granularity: String): List<Int> {
    val startList = timestampToList(start)
    val endList = timestampToList(end)
    var i = startList.size - 1
    while (granularities[i] != granularity) {
      startList[i] = 0
      endList[i] = 0
      i--
    }
    endList[i] = endList[i] + 1
    val submap = map.subMap(startList.toTimestamp(), true, endList.toTimestamp(), false)
    return submap.values.toList()
  }

  private fun timestampToList(timestamp: String): MutableList<Int> {
    return timestamp.split(":").map { it.toInt() }.toMutableList()
  }

  private fun MutableList<Int>.toTimestamp(): String {
    val builder = StringBuilder()
    for (i in this.indices) {
      val elem = this[i].toString()
      if (i == 0) {
        val missingZeroes = 4 - elem.length
        var paddedElem = StringBuilder()
        for (i in 0 until missingZeroes) {
          paddedElem.append("0")
        }
        val paddedStr = paddedElem.toString()
        builder.append("$paddedElem$elem")
      }
      else {
        if (elem.length == 1) {
          builder.append("0$elem")
        }
        else {
          builder.append("$elem")
        }
      }
      if (i != this.size - 1) {
        builder.append(":")
      }
    }
    return builder.toString()
  }

}