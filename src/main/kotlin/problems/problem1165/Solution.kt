package problems.problem1165

class Solution {
  fun calculateTime(keyboard: String, word: String): Int {
    if (word.length == 0) {
      return 0
    }
    val keyMap = mutableMapOf<Char, Int>()
    for (i in 0 until keyboard.length) {
      keyMap[keyboard[i]] = i
    }
    var index = 0
    var totalMoves = 0
    for (c in word.toCharArray()) {
      val nextIndex = keyMap[c]!!
      val moves = if (index < nextIndex) nextIndex - index else index - nextIndex
      totalMoves += moves
      index = nextIndex
    }
    return totalMoves
  }
}