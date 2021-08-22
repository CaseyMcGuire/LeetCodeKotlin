package problems.problem0394

class Solution {
  fun decodeString(s: String): String {
    val decodedStringList = decodeString(s, 0, s.length)
    val decodedString = StringBuilder()
    decodedStringList.forEach { decodedString.append(it) }
    return decodedString.toString()
  }

  private fun decodeString(s: String, startingIndex: Int, endingIndex: Int): List<Char> {
    val list = mutableListOf<Char>()
    var i = startingIndex
    while (i < endingIndex) {
      if (Character.isDigit(s[i])) {
        val numericString = getNumberOfRepeats(s, i)

        val startingBracketIndex = i + numericString.length
        val endingBracketIndex = getEndingIndexOfBracket(s, startingBracketIndex)
        val decodedSubstring = decodeString(s, startingBracketIndex + 1, endingBracketIndex)
        (0 until numericString.toInt()).map { decodedSubstring }.forEach { list.addAll(it) }
        i = endingBracketIndex + 1
      }
      else {
        list.add(s[i])
        i++
      }
    }
    return list
  }

  private fun getNumberOfRepeats(s: String, startingIndex: Int): String {
    val builder = StringBuilder()
    var i = startingIndex
    while (Character.isDigit(s[i])) {
      builder.append(s[i])
      i++
    }
    return builder.toString()
  }

  private fun getEndingIndexOfBracket(s: String, startingIndexOfBracket: Int): Int {
    var startingBrackets = 0
    for (i in startingIndexOfBracket until s.length) {
      if (s[i] == '[') {
        startingBrackets++
      }
      else if (s[i] == ']') {
        startingBrackets--
        if (startingBrackets == 0) {
          return i
        }
      }
    }
    return -1
  }
}


fun main(args: Array<String>) {
  println(Solution().decodeString("3[a]2[bc]"))
  println(Solution().decodeString("3[a2[c]]"))
  println(Solution().decodeString("2[abc]3[cd]ef"))
  println(Solution().decodeString("abc3[cd]xyz"))
}