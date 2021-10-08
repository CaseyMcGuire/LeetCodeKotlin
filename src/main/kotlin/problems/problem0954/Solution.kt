package problems.problem0954

class Solution {
  fun canReorderDoubled(arr: IntArray): Boolean {
    val visitedIndices = mutableSetOf<Int>()
    val numToIndices = mutableMapOf<Int, MutableList<Int>>()
    arr.sort()
    for (i in arr.indices) {
      numToIndices.add(arr[i], i)
    }

    for (i in arr.indices) {
      if (visitedIndices.contains(i)) {
        continue
      }
      val elem = arr[i]
      if (elem < 0 && elem % 2 != 0) {
        return false
      }
      val other = if (elem < 0) elem / 2 else elem * 2
      var otherIndex = numToIndices.getIndexOf(other)
      if (otherIndex == i) {
        otherIndex = numToIndices.getIndexOf(other)
      }
      if (otherIndex == null) {
        return false
      }
      visitedIndices.add(i)
      visitedIndices.add(otherIndex)
    }
    return true
  }

  fun MutableMap<Int, MutableList<Int>>.add(num: Int, index: Int) {
    val existing = this[num]
    if (existing != null) {
      existing.add(index)
    }
    else {
      val list = mutableListOf<Int>()
      list.add(index)
      this[num] = list
    }
  }

  fun MutableMap<Int, MutableList<Int>>.getIndexOf(num: Int): Int? {
    val list = this[num] ?: return null
    val last = list[list.size - 1]
    list.removeAt(list.size - 1)
    if (list.isEmpty()) {
      this.remove(num)
    }
    return last
  }


}