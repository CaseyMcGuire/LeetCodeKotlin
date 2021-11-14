package problems.problem0131

class Solution {
  fun partition(s: String): List<List<String>> {

    val results = mutableListOf<List<String>>()
    val currentList = mutableListOf<String>()
    fun partition(start: Int){
      if (start == s.length) {
        results.add(currentList.toList())
        return
      }
      for (i in start + 1..s.length) {
        val sub = s.substring(start, i)
        if (isPalindrome(sub)) {
          currentList.add(sub)
          partition(i)
          currentList.removeAt(currentList.size - 1)
        }
      }
    }

    partition(0)
    return results
  }

  private fun isPalindrome(s: String): Boolean {

    var i = 0
    var j = s.length - 1
    while (i < j) {
      if (s[i] != s[j]) {
        return false
      }
      i++
      j--
    }
    return true
  }
}

fun main(args: Array<String>) {
  println(Solution().partition("aab"))
  //println(Solution().partition("bb"))
  //println(Solution().partition("dddddddddddd"))
}