package problems.problem0433

import java.util.*

class Solution {
  fun minMutation(start: String, end: String, bank: Array<String>): Int {
    val queue = LinkedList<Mutation>()
    val seenMutations = mutableSetOf<String>()
    queue.addLast(Mutation(start, 0))
    seenMutations.add(start)
    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()
      val str = current.str
      if (str == end) {
        return current.length
      }
      val mutations = getMutations(str, bank)
      for (mutation in mutations) {
        if (!seenMutations.add(mutation)) {
          continue
        }
        queue.addLast(Mutation(mutation, current.length + 1))
      }
    }

    return -1
  }

  private fun getMutations(gene: String, bank: Array<String>): List<String> {
    val validMutations = mutableListOf<String>()
    for (possibleMutation in bank) {
      if (gene.differByOne(possibleMutation)) {
        validMutations.add(possibleMutation)
      }
    }
    return validMutations
  }


  private fun String.differByOne(other: String): Boolean {
    var differences = 0
    for (i in this.indices) {
      if (this[i] != other[i]) {
        differences++
      }
    }
    return differences == 1
  }
}

data class Mutation(val str: String, val length: Int)