package problems.problem2537

class Solution {
  fun countGood(nums: IntArray, k: Int): Long {
    var numGoodSubarrays = 0L
    val numToFrequency = mutableMapOf<Int, Int>()
    var numInWindow = 0
    var i = 0
    var j = 0

    while (true) {

      if (numInWindow >= k) {
        numGoodSubarrays += nums.size - j + 1
        val curFrequency = numToFrequency[nums[i]]!!
        if (curFrequency >= 2) {
          numInWindow -= getSumUpToNum(curFrequency)
        }
        val newFrequency = curFrequency - 1
        if (newFrequency >= 2) {
          numInWindow += getSumUpToNum(newFrequency)
        }
        numToFrequency[nums[i]] = newFrequency
        i++
      }
      else {
        if (j == nums.size) {
          break
        }
        val newNum = nums[j]
        numToFrequency.merge(newNum, 1) { cur, acc -> cur + acc }
        val newFrequency = numToFrequency[newNum]!!
        if (newFrequency >= 2) {
          numInWindow += getSumUpToNum(newFrequency)
        }
        val oldFrequency = newFrequency - 1
        if (oldFrequency >= 2) {
          numInWindow -= getSumUpToNum(oldFrequency)
        }
        j++
      }
    }
    return numGoodSubarrays
  }

  fun getSumUpToNum(num: Int): Int {
    val upTo = num - 1
    return (upTo * (upTo + 1)) / 2
  }
}
