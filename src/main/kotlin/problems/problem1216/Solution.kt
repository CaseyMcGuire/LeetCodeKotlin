package problems.problem1216

class Solution {
  fun isValidPalindrome(s: String, k: Int): Boolean {

    val cache = mutableSetOf<Triplet>()

    fun recurse(i: Int, j: Int, numRemoved: Int): Boolean {
      val triplet = Triplet(i, j, numRemoved)
      if (cache.contains(triplet)) {
        return false
      }
      if (numRemoved > k) {
        return false
      }
      if (i >= j) {
        return true
      }
      val isValid = if (s[i] == s[j]) {
        recurse(i + 1, j - 1, numRemoved)
      }
      else {
        recurse(i + 1, j, numRemoved + 1) || recurse(i, j - 1, numRemoved + 1)
      }
      if (!isValid) {
        cache.add(triplet)
      }
      return isValid
    }
    return recurse(0, s.length - 1, 0)
  }
}

data class Triplet(val i: Int, val j: Int, val removed: Int)