package problems.problem0159

class Solution {
  fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
    val set = mutableMapOf<Char, Int>()
    var pointer1 = 0
    var pointer2 = 0
    var currentMax = 0
    while (pointer2 < s.length) {
      if (set.size == 2 && set[s[pointer2]] == null) {
        set.decrement(s[pointer1])
        pointer1++
      }
      else {
        var currentSize = pointer2 - pointer1 + 1
        if (currentMax < currentSize) {
          currentMax = currentSize
        }
        set.increment(s[pointer2])
        pointer2++
      }

    }
    return currentMax
  }

  fun MutableMap<Char, Int>.increment(c: Char) {
    if (this[c] == null) {
      this[c] = 1
    }
    else {
      this[c] = this[c]!! + 1
    }
  }

  fun MutableMap<Char, Int>.decrement(c: Char) {
    if (this[c] == 1) {
      this.remove(c)
    }
    else {
      this[c] = this[c]!! - 1
    }
  }
}

fun main(args: Array<String>) {
  println(Solution().lengthOfLongestSubstringTwoDistinct("eceba"))
}