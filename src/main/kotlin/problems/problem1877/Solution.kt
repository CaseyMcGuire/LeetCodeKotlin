package problems.problem1877

class Solution {
  fun minPairSum(nums: IntArray): Int {
    val numsList = nums.toMutableList().sorted()
    val smallerList = numsList.subList(0, nums.size / 2)
    val largerList = numsList.subList(nums.size / 2, nums.size).reversed()
    var largestSoFar = 0
    for (i in smallerList.indices) {
      val sum = smallerList[i] + largerList[i]
      if (sum > largestSoFar) {
        largestSoFar = sum
      }
    }
    return largestSoFar
  }
}