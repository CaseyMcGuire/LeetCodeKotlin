package problems.problem0398

class Solution(nums: IntArray) {

  val numToIndices = mutableMapOf<Int, MutableList<Int>>()
  init {
    for (i in nums.indices) {
      val elements = numToIndices[nums[i]] ?: mutableListOf()
      elements.add(i)
      numToIndices[nums[i]] = elements
    }
  }

  fun pick(target: Int): Int {
    val possibleIndices = numToIndices[target]!!
    val randomIndex = possibleIndices.random()
    return randomIndex
  }

}