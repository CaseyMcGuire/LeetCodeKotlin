package problems.problem3371


class Solution {
  fun getLargestOutlier(nums: IntArray): Int {

    val frequencyMap = mutableMapOf<Int, Int>()
    var sum = 0
    for (num in nums) {
      frequencyMap.increment(num)
      sum += num
    }

    var largestOutlierSoFar: Int? = null
    for (outlier in nums) {
      val subsumCanExist = (sum - outlier) % 2 == 0
      if (!subsumCanExist) {
        continue
      }

      frequencyMap.decrement(outlier)
      val subsum = ((sum - outlier) / 2)
      val subsumFrequency = frequencyMap[subsum]
      if (subsumFrequency != null) {
        if (largestOutlierSoFar == null || largestOutlierSoFar < outlier) {
          largestOutlierSoFar = outlier
        }
      }
      frequencyMap.increment(outlier)
    }


    return largestOutlierSoFar ?: 0
  }

  fun MutableMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  fun MutableMap<Int, Int>.decrement(num: Int) {
    val frequency = this[num]
      ?: return

    if (frequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = frequency - 1
    }
  }
}