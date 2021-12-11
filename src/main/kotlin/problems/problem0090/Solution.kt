package problems.problem0090

class Solution {
  fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    val subsets = mutableSetOf<List<Int>>()
    val curList = mutableListOf<Int>()
    fun recurse(i: Int) {
      if (i == nums.size) {
        subsets.add(curList.sorted())
        return
      }
      curList.add(nums[i])
      recurse(i + 1)
      curList.removeAt(curList.size - 1)
      recurse(i + 1)
    }
    recurse(0)
    return subsets.toList()
  }
}