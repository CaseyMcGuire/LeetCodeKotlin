package problems.problem1593

class Solution {
  fun maxUniqueSplit(s: String): Int {
    var curList = mutableListOf<Char>()
    var maxSoFar = 0
    val seen = mutableSetOf<String>()

    fun recurse(i: Int) {
      if (i == s.length) {
        maxSoFar = Math.max(maxSoFar, seen.size)
        return
      }
      curList.add(s[i])

      val temp = curList
      val str = curList.joinToString("")
      var added = seen.add(str)
      curList = mutableListOf<Char>()
      recurse(i + 1)
      if (added) {
        seen.remove(str)
      }
      curList = temp

      if (i != s.length - 1) {
        recurse(i + 1)
      }
    }
    recurse(0)
    return maxSoFar
  }
}