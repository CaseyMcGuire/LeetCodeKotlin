package problems.problem0259

class Solution {
  fun threeSumSmaller(nums: IntArray, target: Int): Int {
    var numTriplets = 0
    fun recurse(size: Int, sum: Int, index: Int) {
      val newSum = sum + nums[index]
      val newSize = size + 1
      if (newSize > 3) {
        return
      }
      if (newSize == 3) {
        if (newSum < target) {
          numTriplets++
        }
        return
      }
      for (i in index + 1 until nums.size) {
        recurse(newSize, newSum, i)
      }
    }
    for (i in 0 until nums.size) {
      recurse(0, 0, i)
    }
    return numTriplets
  }

}

fun main(args: Array<String>) {
  println(Solution().threeSumSmaller(intArrayOf(1,1,-2), 1))
}