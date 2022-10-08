package problems.problem0045

class Solution {
  fun jump(nums: IntArray): Int {
    val indexToMinJumps = mutableMapOf<Int, Int>()

    fun recurse(index: Int): Int {
      if (index == nums.size - 1) {
        return 0
      }
      else if (index >= nums.size) {
        return -1
      }

      val minJumpsCached = indexToMinJumps[index]
      if (minJumpsCached == -1) {
        return minJumpsCached
      }
      if (minJumpsCached != null) {
        return minJumpsCached
      }

      var minSoFar: Int? = null
      for (i in index + 1 .. Math.min(nums.size - 1, index + nums[index])) {
        val minJumpsForNext = recurse(i)
        if (minJumpsForNext == -1) {
          continue
        }
        if (minSoFar == null || minJumpsForNext < minSoFar) {
          minSoFar = minJumpsForNext
        }
      }

      if (minSoFar == null) {
        indexToMinJumps[index] = -1
        return -1
      }

      indexToMinJumps[index] = minSoFar + 1
      return 1 + minSoFar
    }

    for (i in nums.size - 1 downTo 0) {
      recurse(i)
    }
    return recurse(0)
  }
}