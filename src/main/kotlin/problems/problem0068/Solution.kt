package problems.problem0068

import java.util.*

class Solution {
  fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
    val lines = mutableListOf<String>()
    val queue = LinkedList<String>(words.toList())

    while (queue.isNotEmpty()) {
      val curLine = mutableListOf<String>()
      var totalLength = 0
      while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        var nextLength = totalLength + cur.length
        if (curLine.isNotEmpty()) {
          // add space between words
          nextLength++
        }
        if (nextLength > maxWidth) {
          queue.addFirst(cur)
          break
        }
        else {
          curLine.add(cur)
          totalLength = nextLength
        }
      }
      val isLastLine = queue.isEmpty()
      val currentLine = createStringFromWords(curLine, maxWidth, isLastLine)
      lines.add(currentLine)
    }

    return lines
  }

  private fun createStringFromWords(words: List<String>, maxWidth: Int, isLastLine: Boolean): String {
    val builder = StringBuilder()
    if (words.size == 1 || isLastLine) {
      for (i in words.indices) {
        builder.append(words[i])
        if (i != words.size - 1) {
          builder.append(" ")
        }
      }
      for (i in builder.length until maxWidth) {
        builder.append(" ")
      }
      return builder.toString()
    }

    val numSpaces = words.size - 1
    val totalWordsLength = words.map { it.length }.sum()
    val remainingChars = maxWidth - totalWordsLength
    val spaces = Array(numSpaces) { StringBuilder() }
    var j = 0
    for (i in 0 until remainingChars) {
      spaces[j].append(" ")
      j++
      if (j == spaces.size) {
        j = 0
      }
    }

    for (i in words.indices) {
      builder.append(words[i])
      if (i != words.size - 1) {
        builder.append(spaces[i])
      }
    }
    return builder.toString()
  }
}