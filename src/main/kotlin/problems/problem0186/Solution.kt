package problems.problem0186

class Solution {
  fun reverseWords(s: CharArray): Unit {
    reverseEachWord(s)
    var i = 0
    var j = s.size - 1
    while (i < j) {
      val temp = s[i]
      s.swap(i, j)
      i++
      j--
    }
  }

  fun reverseEachWord(s: CharArray) {
    var i = 0
    while (i < s.size) {
      if (s[i] == ' ') {
        i++
        continue
      }
      var j = i
      while (j < s.size && s[j] != ' ') {
        j++
      }

      s.reverse(i, j - 1)
      i = j
    }
  }

  fun CharArray.reverse(start: Int, end: Int) {
    var i = start
    var j = end
    while (i < j) {
      this.swap(i,j)
      i++
      j--
    }
  }

  fun CharArray.swap(first: Int, second: Int) {
    val temp = this[first]
    this[first] = this[second]
    this[second] = temp
  }
}