package problems.problem0169

class Solution {
  fun majorityElement(nums: IntArray): Int {
    val frequencyMap = mutableMapOf<Int, Int>()
    var curMax = 0
    var maxElement: Int? = null
    for (num in nums) {
      frequencyMap.merge(num, 1) { cur, acc -> acc + cur }
      val frequency = frequencyMap[num]!!
      if (maxElement == null) {
        maxElement = num
        curMax = 1
      }
      else if (frequency > curMax) {
        curMax = frequency
        maxElement = num
      }
    }
    return maxElement ?: 0
  }

}