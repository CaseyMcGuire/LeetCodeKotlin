package problems.problem1087

class Solution {
  fun expand(s: String): Array<String> {
    val tokens = tokenize(s)
    val words = mutableListOf<String>()
    fun recurse(index: Int, acc: MutableList<String>) {
      if (index == tokens.size) {
        val word = acc.joinToString("")
        words.add(word)
        return
      }
      val currentToken = tokens[index]
      when (currentToken) {
        is WordToken -> {
          acc.add(currentToken.str)
          recurse(index + 1, acc)
          acc.removeAt(acc.size - 1)
        }
        is OptionToken -> {
          for (word in currentToken.words) {
            acc.add(word)
            recurse(index + 1, acc)
            acc.removeAt(acc.size - 1)
          }
        }
      }
    }
    recurse(0, mutableListOf<String>())
    return words.toTypedArray().sortedArray()
  }

  private fun tokenize(s: String): List<Token> {
    var index = 0
    val tokens = mutableListOf<Token>()
    var currentWord = StringBuilder()
    while (index < s.length) {
      if (s[index] == '{') {
        if (currentWord.isNotEmpty()) {
          tokens.add(WordToken(currentWord.toString()))
          currentWord = StringBuilder()
        }
        val closingBracketIndex = findClosingBracketIndex(s, index)
        val possibleWords = s.substring(index + 1, closingBracketIndex).split(",").toList()
        tokens.add(OptionToken(possibleWords))
        index = closingBracketIndex + 1
      }
      else if (s[index] == ',') {
        tokens.add(WordToken(currentWord.toString()))
        currentWord = StringBuilder()
        index++
      }
      else {
        currentWord.append(s[index])
        index++
      }
    }
    if (currentWord.isNotEmpty()) {
      tokens.add(WordToken(currentWord.toString()))
    }
    return tokens
  }

  private fun findClosingBracketIndex(s: String, index: Int): Int {
    for (i in index until s.length) {
      if (s[i] == '}') {
        return i
      }
    }
    throw RuntimeException()
  }
}

sealed class Token
data class WordToken(val str: String): Token()
data class OptionToken(val words: List<String>): Token()


fun main(args: Array<String>) {
  println(Solution().expand("{a,b}c{d,e}f"))
}