package problems.problem0047

class Solution {
  fun permuteUnique(nums: IntArray): List<List<Int>> {
    val permutations = mutableSetOf<List<Int>>()
    val candidates = nums.toMutableList()
    fun recurse(size: Int, curList: MutableList<Int>) {
      if (size == 0) {
        permutations.add(curList.toList())
        return
      }
      for (i in 0 until size) {
        curList.add(candidates[i])
        candidates.swap(i, size - 1)
        recurse(size - 1, curList)
        candidates.swap(i, size - 1)
        curList.removeAt(curList.size - 1)
      }
    }
    recurse(nums.size, mutableListOf())
    return permutations.toList()
  }

  fun MutableList<Int>.swap(first: Int, second: Int) {
    val temp = this[first]
    this[first] = this[second]
    this[second] = temp
  }
}