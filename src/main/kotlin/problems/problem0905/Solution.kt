package problems.problem0905

class Solution {
  fun sortArrayByParity(nums: IntArray): IntArray {
    val evens = nums.filter {it % 2 == 0}
    val odds = nums.filter { it % 2 == 1}
    return (evens.toList() + odds.toList()).toIntArray()
  }
}