package problems.problem0075

class Solution {
  fun sortColors(nums: IntArray): Unit {
    var start = 0
    var end = nums.size - 1
    var i = 0
    while (i <= end) {
      if (i < start) {
        i = start
      }
      else if (nums[i] == 0 && nums[start] == 0) {
        i++
        start++
      }
      else if (nums[i] == 0) {
        nums.swap(i, start)
        start++
      }
      else if (nums[end] == 2) {
        end--
      }
      else if (nums[i] == 2) {
        nums.swap(i, end)
        end--
      }
      else if (nums[end] == 0) {
        nums.swap(start, end)
        start++
      }
      else if (nums[i] == 2) {
        nums.swap(i, end)
        end--
      }
      else if (nums[end] == 1 && nums[i] == 1) {
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