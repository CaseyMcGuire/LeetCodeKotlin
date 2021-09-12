package problems.problem0216

class Solution {
  fun combinationSum3(k: Int, n: Int): List<List<Int>> {
    val nums = (1..9).toList()
    val combinations = mutableListOf<List<Int>>()
    fun recurse(index: Int, target: Int, currentList: MutableList<Int>) {
      if (target == 0 && currentList.size == k) {
        combinations.add(currentList.toList())
        return
      }
      if (index >= nums.size || target <= 0) {
        return
      }
      currentList.add(nums[index])
      recurse(index + 1, target - nums[index], currentList)
      currentList.removeAt(currentList.size - 1)
      recurse(index + 1, target, currentList)
    }
    recurse(0, n, mutableListOf<Int>())
    return combinations.toList()
  }
}

fun main(args: Array<String>) {
  println(Solution().combinationSum3(3, 7))
}