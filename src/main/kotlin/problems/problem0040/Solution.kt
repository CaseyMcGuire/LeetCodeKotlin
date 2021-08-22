package problems.problem0040

class Solution {
  fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    val currentCombinations = mutableListOf<List<Int>>()
    candidates.sort()
    fun dfs(index: Int, currentList: MutableList<Int>, currentSum: Int) {
      if (currentSum == target) {
        currentCombinations.add(ArrayList(currentList))
        return
      }
      else if (index >= candidates.size || currentSum > target) {
        return
      }
      for (i in index until candidates.size) {
        // I don't know why this works
        if (i != index && candidates[i] == candidates[i - 1]) {
          continue
        }
        currentList.add(candidates[i])
        dfs(i + 1, currentList, currentSum + candidates[i])
        currentList.removeAt(currentList.size - 1)
      }
    }
    dfs(0, mutableListOf(), 0)
    return currentCombinations.map { it.sorted() }.toSet().toList()
  }
}