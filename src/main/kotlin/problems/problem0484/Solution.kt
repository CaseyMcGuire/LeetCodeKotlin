package problems.problem0484

class Solution {
  fun findPermutation(s: String): IntArray {
    if (s.isEmpty()) {
      return intArrayOf()
    }
    var isIncreasing = s[0] == 'I'
    var index = 0

    val permutation = mutableListOf<Int>()
    while (index < s.length) {
      val currentIndices = mutableListOf<Int>()
      if (isIncreasing) {
        while (index < s.length && s[index] == 'I') {
          currentIndices.add(index)
          index++
        }
      }
      else {
        while (index < s.length && s[index] == 'D') {
          currentIndices.add(index)
          index++
        }

        if (index < s.length) {
          currentIndices.add(index)
          index++
        }
      }

      if (isIncreasing) {
        if (index == s.length) {
          currentIndices.add(index)
        }
        for (i in currentIndices) {
          permutation.add(i)
        }
      }
      else {
        val endsAtI = s[currentIndices[currentIndices.size - 1]] == 'I'
        if (index == s.length && !endsAtI) {
          currentIndices.add(index)
        }
        for (i in currentIndices.reversed()) {
          permutation.add(i)
        }
        if (index == s.length && endsAtI) {
          permutation.add(index)
        }
      }
      isIncreasing = !isIncreasing
    }

    return permutation.map{ it + 1 }.toIntArray()
  }
}
