package problems.problem0075

class Solution {
  fun sortColors(nums: IntArray): Unit {
    var start = 0
    var end = nums.size - 1
    var i = 0
    while (i <= end) {
      if (nums[i] == 0) {
        nums.swap(i, start)
        i++
        start++
      }
      else if (nums[i] == 2) {
        nums.swap(i, end)
        end--
      }
      else {
        i++
      }
    }


  }

  private fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
  }
}

fun main(args: Array<String>) {
  //println(Solution().sortColors(intArrayOf(2,0,2,1,1,0)))
  println(Solution().sortColors(intArrayOf(2,0,1)))
}