package problems.problem1324

class Solution {
  fun printVertically(s: String): List<String> {
    val rows = mutableListOf<String>()
    val words = s.split(" ")
    val longest = words.maxBy { it.length }.length

    for (i in 0 until longest) {
      val builder = StringBuilder()

      for (word in words) {
        val nextChar = if (word.length <= i) ' ' else word[i]
        builder.append(nextChar)
      }

      rows.add(builder.toString().trimEnd())
    }

    return rows
  }

}