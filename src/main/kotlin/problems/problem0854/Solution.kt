package problems.problem0854

class Solution {
  fun kSimilarity(s1: String, s2: String): Int {

    if (s1 == s2 || s1.length != s2.length) {
      return 0
    }
    val frequencyMap1 = mutableMapOf<Char, Int>()
    val frequencyMap2 = mutableMapOf<Char, Int>()
    for (i in s1.indices) {
      frequencyMap1.merge(s1[i], 1) { cur, acc -> cur + acc }
      frequencyMap2.merge(s2[i], 1) { cur, acc -> cur + acc }
    }

    if (frequencyMap1 != frequencyMap2) {
      return 0
    }


    val indices = mutableSetOf<Int>()
    val charToIndices = mutableMapOf<Char, MutableSet<Int>>()
    for (i in s1.indices) {
      if (s1[i] != s2[i]) {
        indices.add(i)
      }
    }

    val c1 = s1.toCharArray()
    val c2 = s2.toCharArray()
    var totalMin = Integer.MAX_VALUE
    fun recurse(index: Int, swapsSoFar: Int): Int {
      if (swapsSoFar > totalMin) {
        return totalMin
      }
      if (index == s1.length) {
        totalMin = Math.min(swapsSoFar, totalMin)
        return swapsSoFar
      }
      if (c1[index] == c2[index]) {
        return recurse(index + 1, swapsSoFar)
      }
      var minSoFar = Integer.MAX_VALUE
      for (i in index until c1.size) {
        if (c1[i] == c2[index]) {
          c1.swap(i, index)
          minSoFar = Math.min(minSoFar, recurse(index + 1, swapsSoFar + 1))
          c1.swap(i, index)
        }
      }
      return minSoFar
    }

    return recurse(0,0)
  }

  private fun CharArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
  }
}