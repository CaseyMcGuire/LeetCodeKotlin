package problems.problem0015

import java.util.*

class Solution {
  fun threeSum(nums: IntArray): List<List<Int>> {
    val frequencyMap = mutableMapOf<Int, Int>()
    for (num in nums) {
      frequencyMap.merge(num, 1) { first, second ->
        first + second
      }
    }
    val triplets = mutableSetOf<Triple<Int, Int, Int>>()
    for (i in nums.indices) {
      val firstNum = -nums[i]
      deprecateOrRemove(frequencyMap, nums[i])
      val tempMap = HashMap<Int, Int>(frequencyMap)
      for (j in i + 1 until nums.size) {
        deprecateOrRemove(tempMap, nums[j])
        val sum = firstNum - nums[j]

        val remainder = tempMap[sum]
        if (remainder != null) {
          val sorted = intArrayOf(nums[i], nums[j], sum).sorted()
          triplets.add(Triple(sorted[0], sorted[1], sorted[2]))
        }
      }
    }
    return triplets.map { it.toList() }.toList()
  }

  private fun deprecateOrRemove(map: MutableMap<Int, Int>, num: Int) {
    if (map[num] == 1) {
      map.remove(num)
    }
    else {
      map[num] = map[num]!! - 1
    }
  }
}

fun main(args: Array<String>) {
  println(Solution().threeSum(intArrayOf(-1,0,1,2,-1,-4)))
}