package problems.problem0016

import java.util.*

class Solution {
  fun threeSumClosest(nums: IntArray, target: Int): Int {
    val frequencyMap = TreeMap<Int, Int>()
    for (num in nums) {
      frequencyMap.increment(num)
    }

    var closest: Int? = null
    for (i in 0 until nums.size) {
      frequencyMap.decrement(nums[i])
      val copy = TreeMap(frequencyMap)
      val newTarget = target - nums[i]
      for (j in i + 1 until nums.size) {
        val cur = nums[j]
        copy.decrement(cur)
        val nextTarget = newTarget - cur
        val larger = copy.ceilingKey(nextTarget)
        val smaller = copy.floorKey(nextTarget)
        val largerSum = if (larger != null) nums[i] + nums[j] + larger else null
        val smallerSum = if (smaller != null) nums[i] + nums[j] + smaller else null
        if (largerSum == null && smallerSum == null) {
          continue
        }
        val closer = if (largerSum == null) {
          smallerSum!!
        } else if (smallerSum == null) {
          largerSum!!
        }
        else if (Math.abs(target - largerSum) > Math.abs(target - smallerSum)) {
          smallerSum
        } else {
          largerSum
        }

        if (closest == null || Math.abs(target - closest) > Math.abs(target - closer)) {
          closest = closer
        }
      }
    }
    return closest!!
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val cur = this[num] ?: return
    if (cur == 1) {
      this.remove(num)
    }
    else {
      this[num] = cur - 1
    }
  }
}