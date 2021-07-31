package problems.problem0833

class Solution {
  fun findReplaceString(s: String, indices: IntArray, sources: Array<String>, targets: Array<String>): String {
    val indexMap = mutableMapOf<Int, Int>()
    for (index in indices.indices) {
      indexMap[indices[index]] = index
    }
    val newString = StringBuilder()
    var i = 0
    while (i < s.length) {
      val sourceIndex = indexMap[i]
      if (sourceIndex == null) {
        newString.append(s[i])
        i++
      }
      else {
        if (containsSubstringAtIndex(i, s, sources[sourceIndex])) {
          newString.append(targets[sourceIndex])
          i += sources[sourceIndex].length
        }
        else {
          newString.append(s[i])
          i++
        }
      }

    }
    return newString.toString()
  }

  private fun containsSubstringAtIndex(index: Int, s: String, substring: String): Boolean {
    for (i in substring.indices) {
      val currentIndex = index + i
      if (currentIndex >= s.length) {
        return false
      }
      else if (s[currentIndex] != substring[i]) {
        return false
      }
    }
    return true
  }
}

fun main(args: Array<String>) {
  println(Solution().findReplaceString("abcd", intArrayOf(0, 2), arrayOf("ab", "ec"), arrayOf("eee", "ffff")))
}