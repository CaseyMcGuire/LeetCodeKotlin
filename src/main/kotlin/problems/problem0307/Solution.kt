package problems.problem0307

class NumArray(private val nums: IntArray) {

  private val sums = IntArray(nums.size)

  init {
    updateSums(0)
  }

  fun update(index: Int, `val`: Int) {
    nums[index] = `val`
    updateSums(index)
  }

  fun sumRange(left: Int, right: Int): Int {
    if (left == 0) {
      return sums[right]
    }
    return sums[right] - sums[left - 1]
  }

  private fun updateSums(index: Int) {
    for (i in index until nums.size) {
      if (i == 0) {
        sums[i] = nums[i]
      }
      else {
        sums[i] = nums[i] + sums[i - 1]
      }
    }
  }

}