package problems.problem0271

class Codec {

  // encode ->

  // Encodes a list of strings to a single string.
  fun encode(strs: List<String>): String {
    val strList = mutableListOf<String>()
    for (str in strs) {
      strList.add(str.length.toString() + "|")
      strList.add(str)
    }
    return strList.joinToString("")
  }

  // Decodes a single string to a list of strings.
  fun decode(s: String): List<String> {
    val strList = mutableListOf<String>()
    var i = 0
    while (true) {
      if (i >= s.length) {
        break
      }
      val num = getNumAtIndex(s, i)
      if (num == 0) {
        strList.add("")
        i += 2
        continue
      }

      val numLength = num.toString().length
      val strStart = i + numLength + 1
      val strEnd = strStart + num
      val str = s.substring(strStart, strEnd)
      strList.add(str)
      i = strEnd
    }
    return strList
  }

  private fun getNumAtIndex(s: String, i: Int): Int {
    val builder = StringBuilder()
    var index = i
    while (s[index] != '|') {
      builder.append(s[index])
      index++
    }
    return builder.toString().toInt()
  }
}