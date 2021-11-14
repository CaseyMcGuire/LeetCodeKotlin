package problems.problem0280

class Solution {
  fun wiggleSort(nums: IntArray): Unit {
    nums.sort()
    if (nums.size <= 2) {
      return
    }

    val other = IntArray(nums.size)
    var i = 0
    var j = nums.size - 1
    var iter = 0
    var first = true
    while (i <= j) {
      if (first) {
        other[iter] = nums[i]
        i++
      }
      else {
        other[iter] = nums[j]
        j--
      }
      first = !first
      iter++
    }

    for (i in other.indices) {
      nums[i] = other[i]
    }
  }

  private fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
  }
}