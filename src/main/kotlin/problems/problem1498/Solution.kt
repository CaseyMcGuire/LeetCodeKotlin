package problems.problem1498

class Solution {
  fun numSubseq(nums: IntArray, target: Int): Int {
    var total = 0.toBigInteger()
    nums.sort()
    var j = nums.size - 1
    for (i in nums.indices) {
      if (nums[i] > target) {
        break
      }


      while (j >= 0 && nums[i] + nums[j] > target) {
        j--
      }

      if (j < i || nums[i] + nums[j] > target) {
        break
      }

      val difference = j - i
      total += 2.toBigInteger().pow(j - i)
    }
    val mod = Math.pow(10.0, 9.0).toInt() + 7
    return (total % mod.toBigInteger()).toInt()
  }
}