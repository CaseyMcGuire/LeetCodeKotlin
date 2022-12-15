package problems.problem1570

class SparseVector(nums: IntArray) {
  private val numsMap = mutableMapOf<Int, Int>()

  init {
    for (i in nums.indices) {
      if (nums[i] != 0) {
        numsMap[i] = nums[i]
      }
    }
  }

  fun dotProduct(vec: SparseVector): Int {
    val commonIndices = vec.numsMap.keys.intersect(numsMap.keys)
    return commonIndices.map { numsMap[it]!! * vec.numsMap[it]!! }
      .sum()
  }
}