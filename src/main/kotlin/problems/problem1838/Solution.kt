package problems.problem1838

class Solution {
  fun maxFrequency(nums: IntArray, k: Int): Int {
    nums.sort()
    var curLargest = 0L
    var remaining = k.toLong()
    var longestSoFar = 0
    var i = 0
    var j = 0
    while (true) {
      if (remaining < 0) {
        val removedElement = nums[i]
        i++

        remaining += (curLargest - removedElement.toLong())
      }
      else {
        longestSoFar = Math.max(longestSoFar, j - i)
        if (j == nums.size) {
          break
        }
        val prevSize = j - i
        val prevLargest = curLargest
        curLargest = nums[j].toLong()
        j++
        remaining -= ((curLargest - prevLargest) * prevSize.toLong())

      }
    }

    return longestSoFar
  }
}