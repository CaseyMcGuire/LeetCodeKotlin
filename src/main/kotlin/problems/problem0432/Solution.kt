package problems.problem0432

import java.util.*

class AllOne() {

  private val stringToCount = TreeMap<String, Int>()
  private val countToString = TreeMap<Int, MutableSet<String>>()

  fun inc(key: String) {
    val currentCount = stringToCount[key]
    if (currentCount != null) {
      val prevSet = countToString[currentCount]!!
      prevSet.remove(key)
      if (prevSet.isEmpty()) {
        countToString.remove(currentCount)
      }
    }
    stringToCount.merge(key, 1) { cur, acc -> cur + acc }
    val count = stringToCount[key]!!
    val set = countToString[count] ?: mutableSetOf()
    set.add(key)
    countToString[count] = set
  }

  fun dec(key: String) {
    val currentCount = stringToCount[key]
    if (currentCount == null) {
      return
    }
    val curSet = countToString[currentCount]!!
    curSet.remove(key)
    if (curSet.isEmpty()) {
      countToString.remove(currentCount)
    }
    val newCount = currentCount - 1
    if (newCount == 0) {
      stringToCount.remove(key)
    }
    else {
      stringToCount[key] = newCount
      val set = countToString[newCount] ?: mutableSetOf()
      set.add(key)
      countToString[newCount] = set
    }
  }

  fun getMaxKey(): String {
    val last = countToString.lastEntry()
    if (last == null) {
      return ""
    }
    for (elem in last.value) {
      return elem
    }
    return ""
  }

  fun getMinKey(): String {
    val first = countToString.firstEntry()
    if (first == null) {
      return ""
    }
    for (elem in first.value) {
      return elem
    }
    return ""
  }

}