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

  fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    val sortedCandidates = candidates.sorted()
    val sums = mutableListOf<List<Int>>()
    fun findSums(index: Int, currentSum: Int, currentList: MutableList<Int>) {
      if (currentSum == target) {
        sums.add(currentList.toList())
        return
      }
      if (index >= sortedCandidates.size || currentSum > target) {
        return
      }
      val currentNum = sortedCandidates[index]
      currentList.add(currentNum)
      findSums(index, currentSum + currentNum, currentList)
      findSums(index + 1, currentSum + currentNum, currentList)
      currentList.removeAt(currentList.size - 1)
      findSums(index + 1, currentSum, currentList)
    }
    findSums(0, 0, mutableListOf())
    return sums.map { it.sorted() }.toSet().toList()
  }

}