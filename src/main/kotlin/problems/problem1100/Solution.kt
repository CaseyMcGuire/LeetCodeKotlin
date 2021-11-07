package problems.problem1100

class Solution {
  fun numKLenSubstrNoRepeats(s: String, k: Int): Int {
    if (s.length < k) {
      return 0
    }
    val frequencyMap = mutableMapOf<Char, Int>()
    for (i in 0 until k) {
      frequencyMap.increment(s[i])
    }

    var num = 0
    if (frequencyMap.size == k) {
      num++
    }

    for (i in 1..s.length - k) {
      frequencyMap.decrement(s[i - 1])
      frequencyMap.increment(s[i + k - 1])
      if (frequencyMap.size == k) {
        num++
      }
    }
    return num
  }

  fun MutableMap<Char, Int>.increment(char: Char) {
    this.merge(char, 1) { cur, acc -> cur + acc }
  }

  fun MutableMap<Char, Int>.decrement(char: Char) {
    val existing = this[char]
    if (existing == null) {
      return
    }
    if (existing == 1) {
      this.remove(char)
    }
    else {
      this[char] = existing - 1
    }
  }
}