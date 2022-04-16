package problems.problem0752

import java.util.*

class Solution {
  fun openLock(deadends: Array<String>, target: String): Int {
    val deadendSet = deadends.toSet()
    val visited = mutableSetOf<String>()
    val queue = LinkedList<Move>()
    queue.addLast(Move("0000", 0))
    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      if (deadendSet.contains(cur.combo)) {
        continue
      }
      if (cur.combo == target) {
        return cur.moves
      }
      if (!visited.add(cur.combo)) {
        continue
      }
      val nextCombos = getNextCombos(cur.combo)
      for (combo in nextCombos) {
        queue.addLast(Move(combo, cur.moves + 1))
      }
    }
    return -1
  }

  private fun getNextCombos(combo: String): List<String> {
    val elems = combo.toCharArray()
    val combos = mutableListOf<String>()
    for (i in elems.indices) {
      val value = Character.getNumericValue(elems[i])
      val next = if (value == 9) 0 else value + 1
      val prev = if (value == 0) 9 else value - 1
      elems[i] = next.toString()[0]
      combos.add(elems.joinToString(""))
      elems[i] = prev.toString()[0]
      combos.add(elems.joinToString(""))
      elems[i] = value.toString()[0]
    }
    return combos
  }
}

data class Move(val combo: String, val moves: Int)