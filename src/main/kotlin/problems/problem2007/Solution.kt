package problems.problem2007

class Solution {
  fun findOriginalArray(changed: IntArray): IntArray {
    if (changed.isEmpty()) {
      return IntArray(0)
    }
    if (changed.size % 2 == 1) {
      return IntArray(0)
    }
    changed.sort()
    val map = mutableMapOf<Int, Int>()
    for (num in changed) {
      map.increment(num)
    }
    val originalList = mutableListOf<Int>()
    for (num in changed) {
      if (map[num] == null) {
        continue
      }
      val double = num * 2
      map.decrement(num)
      val frequency = map[double]
      if (frequency == null) {
        return IntArray(0)
      }
      originalList.add(num)
      map.decrement(double)
      if (map.isEmpty()) {
        break
      }
    }

    return originalList.toIntArray()
  }

  private fun MutableMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun MutableMap<Int, Int>.decrement(num: Int) {
    val value = this[num]
    if (value == null) {
      return
    }
    if (value == 1) {
      this.remove(num)
    }
    else {
      this[num] = value - 1
    }
  }
}