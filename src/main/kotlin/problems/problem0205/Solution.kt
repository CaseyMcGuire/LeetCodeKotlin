package problems.problem0205

class Solution {
  fun isIsomorphic(s: String, t: String): Boolean {
    val characterMap = mutableMapOf<Char, Char>()
    val seenSecondChars = mutableSetOf<Char>()
    for (i in s.indices) {
      val firstChar = s[i]
      val secondChar = t[i]
      val mappedChar = characterMap[firstChar]
      if (mappedChar == null) {
        if (!seenSecondChars.add(secondChar)) {
          return false
        }
        characterMap[firstChar] = secondChar
      }
      else if (mappedChar != secondChar){
        return false
      }
    }
    return true
  }
}