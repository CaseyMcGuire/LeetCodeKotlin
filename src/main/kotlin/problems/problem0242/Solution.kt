package problems.problem0242

class Solution {
  fun isAnagram(s: String, t: String): Boolean {
    return s.toList().sorted() == t.toList().sorted()
  }
}