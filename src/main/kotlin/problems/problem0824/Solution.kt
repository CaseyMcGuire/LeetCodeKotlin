package problems.problem0824

class Solution {
  fun toGoatLatin(sentence: String): String {
    val words = sentence.split(" ")
    val goatLatinWords = mutableListOf<String>()
    for (i in words.indices) {
      goatLatinWords.add(words[i].toGoatLatinWord(i + 1))
    }
    return goatLatinWords.joinToString(" ")
  }

  private fun String.toGoatLatinWord(index: Int): String {
    val builder = StringBuilder()
    val firstCharIsVowel = this.first().isVowel()
    if (firstCharIsVowel) {
      builder.append(this)

    }
    else {
      builder.append(this.substring(1, this.length))
      builder.append(this.first())
    }
    builder.append("ma")
    for (i in 0 until index) {
      builder.append('a')
    }
    return builder.toString()
  }

  private fun Char.isVowel(): Boolean {
    return when (this.lowercaseChar()) {
      'a', 'i', 'e', 'o', 'u' -> true
      else -> false
    }
  }
}