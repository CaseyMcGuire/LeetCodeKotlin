package problems.problem0408

class Solution {
  fun validWordAbbreviation(word: String, abbr: String): Boolean {
    var i = 0
    var j = 0

    while (true) {
      if (i == word.length && j == abbr.length) {
        return true
      }
      else if (i >= word.length || j >= abbr.length) {
        return false
      }

      val startsNum = abbr[j].toString().toIntOrNull()
      if (startsNum == 0) {
        return false
      }
      if (startsNum != null)  {
        val num = getNumAtIndex(abbr, j)
        j += num.toString().length
        i += num
      }
      else {
        if (word[i] != abbr[j]) {
          return false
        }
        i++
        j++
      }
    }
  }

  private fun getNumAtIndex(s: String, i: Int): Int {
    val builder = StringBuilder()
    var index = i
    while (index < s.length && s[index].toString().toIntOrNull() != null) {
      builder.append(s[index])
      index++
    }
    return builder.toString().toInt()
  }
}