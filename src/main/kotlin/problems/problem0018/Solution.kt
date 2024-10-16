package problems.problem0018

class Solution {
  fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
    val sorted = getSortedList(nums)
    val curList = mutableListOf<Int>()
    val sums = mutableSetOf<List<Int>>()

    fun recurse(startIndex: Int, remaining: Long) {
      if (curList.size == 4) {
        if (remaining == 0L) {
          sums.add(curList.toList())
        }
        return
      }
      if (startIndex == sorted.size) {
        return
      }

      for (i in startIndex until sorted.size) {
        val newTarget = remaining - sorted[i]
        curList.add(sorted[i])
        recurse(i + 1, newTarget)
        curList.removeLast()
      }
    }
    recurse(0, target.toLong())
    return sums.toList()
  }

  fun getSortedList(nums: IntArray): List<Int> {
    val frequencyMap  = mutableMapOf<Int, Int>()
    val sortedList = mutableListOf<Int>()
    for (num in nums.sorted()) {
      val frequency = frequencyMap[num] ?: 0
      if (frequency < 4) {
        sortedList.add(num)
      }
      frequencyMap.merge(num, 1) { cur, acc -> cur + acc }
    }
    return sortedList
  }
}