package problems.problem0680

class Solution {
  fun validPalindrome(s: String): Boolean {

    fun isValidPalindrome(i: Int, j: Int, deleted: Boolean): Boolean {
      if (i >= j) {
        return true
      }
      if (s[i] != s[j]) {
        if (deleted) {
          return false
        }
        else {
          return isValidPalindrome(i + 1, j, true) ||
              isValidPalindrome(i, j - 1, true)
        }
      }
      else {
        return isValidPalindrome(i + 1, j - 1, deleted)
      }
    }
    return isValidPalindrome(0, s.length - 1, false)
  }
}