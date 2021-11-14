package problems.problem0131

class Solution {
  fun partition(s: String): List<List<String>> {
    return partition(s, 0, s.length, mutableMapOf())?.toSet()?.toList() ?: emptyList()
  }

  private fun partition(s: String, start: Int, end: Int, cache: MutableMap<Window, List<List<String>>?>): List<List<String>>? {
    if (start == end) {
      return emptyList()
    }
    val window = Window(start, end)
    if (cache.containsKey(window)) {
      return cache[window]
    }

    if (end - start == 1) {
      return listOf(listOf(s[start].toString()))
    }
    val initialList = s.substring(start, end).map { it.toString() }
    val validPartitions = mutableListOf<List<String>>(initialList)
    val substringSize = end - start
    for (windowSize in  2..substringSize) {
      for (windowStart in start..end - windowSize) {
        val windowEnd = windowStart + windowSize
        val leftPartitions = partition(s, start, windowStart, cache)
        val rightPartitions = partition(s, windowEnd, end, cache)
        if (!isPalindrome(s, windowStart, windowEnd) || leftPartitions == null || rightPartitions == null) {
          continue
        }
        val sublist = listOf(s.substring(windowStart, windowEnd))
        if (leftPartitions.isEmpty() && rightPartitions.isEmpty()) {
          validPartitions.add(sublist)
        }
        else if (leftPartitions.isEmpty()) {
          for (rightPartition in rightPartitions) {
            validPartitions.add(sublist + rightPartition)
          }
        }
        else if (rightPartitions.isEmpty()) {
          for (leftPartition in leftPartitions) {
            validPartitions.add(leftPartition + sublist)
          }
        }
        else {
          for (leftPartition in leftPartitions) {
            for (rightPartition in rightPartitions) {
              validPartitions.add(leftPartition + sublist + rightPartition)
            }
          }
        }
      }
    }

    val toReturn = if (validPartitions.isEmpty()) {
      null
    } else {
      validPartitions
    }

    cache[window] = toReturn
    return toReturn
  }

  private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
    if (end - start == 1) {
      return true
    }

    var i = start
    var j = end - 1
    while (i < j) {
      if (s[i] != s[j]) {
        return false
      }
      i++
      j--
    }
    return true
  }
}

data class Window(val start: Int, val end: Int)

fun main(args: Array<String>) {
  println(Solution().partition("aab"))
  //println(Solution().partition("bb"))
  //println(Solution().partition("dddddddddddd"))
}