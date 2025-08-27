package problems.problem0091

class Solution {
  fun numDecodings(s: String): Int {
    if (s.startsWith("0")) {
      return 0
    }
    val cache = mutableMapOf<Int, Int>()

    fun recurse(i: Int): Int {
      if (i == s.length) {
        return 1
      }
      else if (i >= s.length) {
        return 0
      }
      val cachedValue = cache[i]
      if (cachedValue != null) {
        return cachedValue
      }

      var result = if (s[i] != '0') recurse(i + 1) else 0
      val c = s[i]
      val isDoubleDigit = c == '1' || (c == '2' && s.getOrNull(i + 1) in '0'..'6')
      if (isDoubleDigit) {
        result += recurse(i + 2)
      }
      cache[i] = result
      return result
    }
    return recurse(0)
  }
}