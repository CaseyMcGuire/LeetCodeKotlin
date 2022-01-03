package problems.problem0443

class Solution {
  fun compress(chars: CharArray): Int {
    if (chars.isEmpty()) {
      return 0
    }
    var currentIndex = 0
    var curChar = chars[0]
    var numCurChar = 0
    for (i in chars.indices) {
      if (chars[i] == curChar) {
        numCurChar++
        continue
      }
      chars[currentIndex] = curChar
      currentIndex++
      if (numCurChar != 1) {
        for (c in numCurChar.toString()) {
          chars[currentIndex] = c
          currentIndex++
        }
      }
      curChar = chars[i]
      numCurChar = 1
    }
    chars[currentIndex] = curChar
    currentIndex++
    if (numCurChar != 1) {
      for (c in numCurChar.toString()) {
        chars[currentIndex] = c
        currentIndex++
      }
    }
    return currentIndex
  }
}