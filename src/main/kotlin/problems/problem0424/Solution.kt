package problems.problem0424

class Solution {
  fun characterReplacement(s: String, k: Int): Int {
    if (s.isEmpty()) {
      return 0
    }
    if (s.length == 1) {
      return 1
    }
    val frequencyMap = FrequencyMap()

    var start = 0
    var end = 0
    var currentMax = 0
    while (true) {
      val size = frequencyMap.size()
      val largestFrequency = frequencyMap.largestFrequency()
      val numLeft = size - largestFrequency

      if (numLeft <= k) {
        if (size > currentMax) {
          currentMax = size
        }
        if (end == s.length) {
          break
        }
        frequencyMap.increment(s[end])
        end++
      }
      else {
        frequencyMap.decrement(s[start])
        start++
      }
    }
    return currentMax
  }
}

class FrequencyMap {

  private val map = mutableMapOf<Char, Int>()
  private var size = 0

  fun increment(c: Char) {
    map.merge(c, 1) { cur, acc -> cur + acc}
    size++
  }

  fun decrement(c: Char) {
    val currentCount = map[c]
    if (currentCount == null) {
      return
    }
    if (currentCount == 1) {
      map.remove(c)
      size--
      return
    }
    map[c] = currentCount - 1
    size--
  }

  fun largestFrequency(): Int {
    return map.values.max() ?: 0
  }

  fun size(): Int {
    return this.size
  }
}

fun main(args: Array<String>) {
  val solution = Solution()
  println(solution.characterReplacement("AABABBA", 1))
  println(solution.characterReplacement("ABAB", 2))
  println(solution.characterReplacement("AAAB",0))
}