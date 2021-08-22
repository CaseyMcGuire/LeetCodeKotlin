package problems.problem0077

class Solution {
  fun combine(n: Int, k: Int): List<List<Int>> {
    val combinations = mutableListOf<List<Int>>()

    fun recurse(index: Int, currentList: MutableList<Int>) {
      if (currentList.size == k) {
        combinations.add(currentList.toList())
        return
      }
      if (index > n) {
        return
      }
      currentList.add(index)
      recurse(index + 1, currentList)
      currentList.removeAt(currentList.size - 1)
      recurse(index + 1, currentList)
    }
    recurse(1, mutableListOf())
    return combinations
  }
}