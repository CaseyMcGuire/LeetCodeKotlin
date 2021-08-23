package problems.problem0981

import java.util.*

class TimeMap {

  // map key -> (timestamp -> value)
  private val stringToTimeStamps = mutableMapOf<String, TreeMap<Int, String>>()

  fun set(key: String, value: String, timestamp: Int) {
    var existingValueTimestampMap = stringToTimeStamps[key]
    if (existingValueTimestampMap == null) {
      existingValueTimestampMap = TreeMap<Int, String>()
    }

    existingValueTimestampMap[timestamp] = value
    stringToTimeStamps[key] = existingValueTimestampMap
  }

  fun get(key: String, timestamp: Int): String {
    val timeStampsForKey = stringToTimeStamps[key] ?: return ""
    val lowestTimestamp = timeStampsForKey.floorKey(timestamp) ?: return ""
    return timeStampsForKey[lowestTimestamp]!!
  }
}