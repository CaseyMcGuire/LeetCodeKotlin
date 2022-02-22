package problems.problem0345

class Solution {
  fun reverseVowels(s: String): String {
    val newString = StringBuilder()
    val vowels = mutableListOf<Char>()
    val indices = mutableListOf<Int>()

    for (i in s.indices) {
      newString.append(s[i])
      if (isVowel(s[i])) {
        vowels.add(s[i])
        indices.add(i)
      }
    }

    val reversedVowels = vowels.reversed()
    for (i in reversedVowels.indices) {
      val index = indices[i]
      newString[index] = reversedVowels[i]
    }

    return newString.toString()
  }

  private fun isVowel(c: Char): Boolean {
    return when(c) {
      'a' -> true
      'A' -> true
      'e' -> true
      'E' -> true
      'i' -> true
      'I' -> true
      'o' -> true
      'O' -> true
      'u' -> true
      'U' -> true
      else -> false
    }
  }
}