package problems.problem0146

import java.util.*

class LRUCache(val capacity: Int) {

  private val keyToValue = mutableMapOf<Int, ValueAndTimestamp>()
  private val timestampToKey = TreeMap<Int, Int>()
  private var currentTimestamp = 0

  fun get(key: Int): Int {
    val value = keyToValue[key]
    if (value == null) {
      return -1
    }

    timestampToKey[currentTimestamp] = key
    timestampToKey.remove(value.timestamp)
    keyToValue[key] = ValueAndTimestamp(value.value, currentTimestamp)
    currentTimestamp++
    return value.value
  }

  fun put(key: Int, value: Int) {
    val currentVal = keyToValue[key]
    val newVal = ValueAndTimestamp(value, currentTimestamp)
    if (currentVal != null) {

      timestampToKey.remove(currentVal.timestamp)
      timestampToKey[currentTimestamp] = key
      keyToValue[key] = newVal
      currentTimestamp++
      return
    }


    keyToValue[key] = newVal
    if (keyToValue.size > capacity) {
      val lowestEntry = timestampToKey.firstEntry()
      val lowestKey = lowestEntry.value
      val oldTimestamp = lowestEntry.key
      keyToValue.remove(lowestKey)
      timestampToKey.remove(oldTimestamp)
    }
    timestampToKey[currentTimestamp] = key
    currentTimestamp++
  }

}

data class ValueAndTimestamp(val value: Int, val timestamp: Int)
