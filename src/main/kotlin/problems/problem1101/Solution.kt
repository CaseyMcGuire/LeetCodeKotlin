package problems.problem1101

class Solution {
  fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
    val unions = mutableMapOf<Int, Union>()
    for (i in 0 until n) {
      unions[i] = Union(i)
    }
    val logList = logs.map { Log(it[0], it[1], it[2])}.sortedBy { it.timestamp }
    for (log in logList) {
      val first = unions[log.first]!!
      val second = unions[log.second]!!
      // already united
      if (first === second) {
        continue
      }
      first.union(second)

      if (first.size() == n) {
        return log.timestamp
      }
    }
    return -1
  }
}

class Union(val num: Int) {
  var parent: Union? = null
  var size = 1

  fun union(other: Union) {
    var curParent = this
    while (curParent.parent != null) {
      curParent = curParent.parent!!
    }

    var otherParent = other
    while (otherParent.parent != null) {
      otherParent = otherParent.parent!!
    }

    if (curParent.num == otherParent.num) {
      return
    }
    otherParent.parent = curParent
    curParent.size += otherParent.size
  }

  fun size(): Int {
    var cur = this
    while (cur.parent != null) {
      cur = cur.parent!!
    }
    return cur.size
  }
}

data class Node(val num: Int)
data class Log(val timestamp: Int, val first: Int, val second: Int)
