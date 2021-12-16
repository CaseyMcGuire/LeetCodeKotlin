package problems.problem1079

import java.util.*

class Solution {
  fun numTilePossibilities(tiles: String): Int {
    val frequencyMap = createFrequencyMap(tiles)
    val sequences = mutableSetOf<String>()
    val curStr = LinkedList<Char>()
    fun recurse(targetLength: Int, curLength: Int) {
      if (targetLength == curLength) {
        sequences.add(curStr.joinToString(""))
        return
      }
      for (c in frequencyMap.keys.toList()) {
        curStr.addLast(c)
        frequencyMap.decrement(c)
        recurse(targetLength, curLength + 1)
        curStr.removeLast()
        frequencyMap.increment(c)
      }
    }
    for (i in 1..tiles.length) {
      recurse(i, 0)
    }
    return sequences.size
  }

  private fun createFrequencyMap(tiles: String): MutableMap<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    for (c in tiles) {
      map.increment(c)
    }
    return map
  }

  private fun MutableMap<Char, Int>.increment(c: Char) {
    this.merge(c, 1) { acc, cur -> acc + cur }
  }

  private fun MutableMap<Char, Int>.decrement(c: Char) {
    val currentFrequency = this[c] ?: return
    if (currentFrequency == 1) {
      this.remove(c)
    }
    else {
      this[c] = currentFrequency - 1
    }
  }
}