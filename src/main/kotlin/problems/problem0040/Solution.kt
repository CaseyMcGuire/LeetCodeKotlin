package problems.problem0040

import java.util.*

class Solution {
  fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val candidateTree = TreeSet<Int>(candidates.toList())
    val sums = mutableListOf<List<Int>>()
    fun findSums(currentSum: Int, currentList: MutableList<Int>) {
      if (currentSum == target) {
        sums.add(currentList.toList())
        return
      }
      val remaining = target - currentSum
      val smallerElements = candidateTree.headSet(remaining, true)
      smallerElements.forEach {
        currentList.add(it)
        findSums(currentSum + it,currentList)
        currentList.removeAt(currentList.size - 1)
      }
    }
    findSums(0, mutableListOf())
    return sums.map { it.sorted() }.toSet().toList()
  }
}