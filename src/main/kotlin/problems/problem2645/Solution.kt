package problems.problem2645

class Solution {
  fun addMinimum(word: String): Int {
    var additions = 0
    if (word.first() == 'b') {
      additions++
    }
    else if (word.first() == 'c') {
      additions += 2
    }

    for (i in 1 until word.length) {
      if (word[i] == word[i - 1]) {
        additions += 2
      }
      else if (word[i] != word[i - 1].getNextChar()) {
        additions++
      }
    }

    if (word.last() == 'b') {
      additions++
    }
    else if (word.last() == 'a') {
      additions += 2
    }

    return additions
  }
  private fun Char.getNextChar(): Char {
    return when(this) {
      'a' -> 'b'
      'b' -> 'c'
      'c' -> 'a'
      else -> throw IllegalStateException("Invalid character: $this")
    }
  }
}