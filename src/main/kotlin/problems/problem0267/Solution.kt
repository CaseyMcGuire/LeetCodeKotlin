package problems.problem0267

import java.util.*

class Solution {
  fun generatePalindromes(s: String): List<String> {
    val frequencyMap = getFrequencyMap(s)
    val oddFrequencies = frequencyMap.entries.filter { it.value % 2 != 0 }
    if (oddFrequencies.size > 1) {
      return emptyList()
    }
    val currentPermutation = LinkedList<Char>()
    if (oddFrequencies.size == 1) {
      val oddElement = oddFrequencies[0].key
      val frequency = oddFrequencies[0].value
      currentPermutation.addFirst(oddElement)
      if (frequency > 1) {
        frequencyMap[oddElement] = frequency - 1
      }
      else {
        frequencyMap.remove(oddElement)
      }
    }

    val palindromePermutations = mutableSetOf<String>()
    val possibleChars = frequencyMap.toFrequencyList()

    fun recurse(chars: MutableList<Char>, size: Int, permutation: LinkedList<Char>) {
      if (size == 0) {
        palindromePermutations.add(permutation.joinToString(""))
        return
      }

      for (i in 0 until size) {
        permutation.addFirst(chars[i])
        permutation.addLast(chars[i])
        chars.swap(i, size - 1)
        recurse(chars, size - 1, permutation)
        chars.swap(i, size - 1)
        permutation.removeFirst()
        permutation.removeLast()
      }
    }
    recurse(possibleChars, possibleChars.size, currentPermutation)
    return palindromePermutations.toList()

  }

  private fun MutableList<Char>.swap(first: Int, second: Int) {
    val temp = this[first]
    this[first] = this[second]
    this[second] = temp
  }

  private fun Map<Char, Int>.toFrequencyList(): MutableList<Char> {
    val list = mutableListOf<Char>()
    for (entry in this.entries) {
      val c = entry.key
      val halfFrequency = entry.value / 2
      for (i in 0 until halfFrequency) {
        list.add(c)
      }
    }
    return list
  }

  fun getFrequencyMap(s: String): MutableMap<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    for (c in s) {
      map.merge(c, 1) { cur, acc -> cur + acc }
    }
    return map
  }
}