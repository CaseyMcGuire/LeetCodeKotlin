package problems.problem0370

class Solution {
  fun getModifiedArray(length: Int, updates: Array<IntArray>): IntArray {
    val updatesList = updates.map { Update(it[0], it[1], it[2]) }
    val arr = IntArray(length)
    for (update in updatesList) {
      for (i in update.start..update.end) {
        arr[i] += update.increment
      }
    }
    return arr
  }
}

data class Update(val start: Int, val end: Int, val increment: Int)