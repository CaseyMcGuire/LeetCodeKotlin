package problems.problem0046

class Solution {
  fun permute(nums: IntArray): List<List<Int>> {
    val permutations = mutableListOf<List<Int>>()
    fun recurse(currentList: MutableList<Int>, currentSize: Int) {
      if (currentList.size == nums.size) {
        permutations.add(currentList.toList())
        return
      }
      for (i in 0 until currentSize) {
        currentList.add(nums[i])
        nums.swap(i, currentSize - 1)
        recurse(currentList, currentSize - 1)
        currentList.removeAt(currentList.size - 1)
        nums.swap(currentSize - 1, i)
      }
    }
    recurse(mutableListOf(), nums.size)
    return permutations
  }

  private fun IntArray.swap(first: Int, second: Int) {
    val temp = this[first]
    this[first] = this[second]
    this[second] = temp
  }
}