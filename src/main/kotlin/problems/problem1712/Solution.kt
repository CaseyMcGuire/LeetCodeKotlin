package problems.problem1712

class Solution {
  fun waysToSplit(nums: IntArray): Int {
    var numWays = 0.toBigInteger()
    val sumArray = LongArray(nums.size)

    for (i in nums.indices) {
      if (i == 0) {
        sumArray[i] = nums[i].toLong()
      }
      else {
        sumArray[i] = nums[i] + sumArray[i - 1]
      }
    }

    fun findMiddle(start: Int, finish: Int, findRight: Boolean): Int {
      var low = start
      var high = finish
      var bound = -1
      while (low <= high) {
        val mid = ((low.toLong() + high.toLong()) / 2L).toInt()
        val leftSum = sumArray[start - 1]
        val midSum = sumArray[mid] - sumArray[start - 1]
        val rightSum = sumArray[sumArray.size - 1] - sumArray[mid]

        if (leftSum <= midSum && midSum <= rightSum) {
          bound = mid
          if (findRight)  {
            low = mid + 1
          }
          else {
            high = mid - 1
          }
        }
        else if (midSum > rightSum) {
          high = mid - 1
        }
        else {
          low = mid + 1
        }
      }
      return bound
    }

    for (i in 0 until nums.size - 2) {

      var low = i + 1
      var high = nums.size - 2

      val leftWall = findMiddle(low, high, false)
      val rightWall = findMiddle(low, high, true)

      if (leftWall != -1 && rightWall != -1 && rightWall >= leftWall) {
        numWays += (rightWall - leftWall + 1).toBigInteger()
      }
    }

    val power = Math.pow(10.0, 9.0).toInt().toBigInteger() + 7.toBigInteger()
    return (numWays % power).toInt()
  }
}