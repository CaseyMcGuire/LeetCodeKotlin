package problems.problem1482

class Solution {
  fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
    val uniqueDays = bloomDay.toSet().toList().sorted()
    var low = 0
    var high = uniqueDays.size - 1

    while (low < high) {
      var middle = ((low.toLong() + high.toLong()) / 2L).toInt()
      val day = uniqueDays[middle]
      if (canMakeBouquets(bloomDay, m, k, day)) {
        if (middle == 0) {
          return day
        }
        else {
          high = middle - 1
        }
      }
      else {
        low = middle + 1
      }
    }

    if (canMakeBouquets(bloomDay, m, k, uniqueDays[low])) {
      return uniqueDays[low]
    }
    else if (low + 1 < uniqueDays.size - 1 && canMakeBouquets(bloomDay, m, k, uniqueDays[low + 1])) {
      return uniqueDays[low + 1]
    }
    else {
      return -1
    }
  }

  private fun canMakeBouquets(bloomDay: IntArray, m: Int, k: Int, day: Int): Boolean {
    var curRun = 0
    var numBouquets = 0
    for (i in bloomDay.indices) {
      if (bloomDay[i] <= day) {
        curRun++
      }
      else {
        curRun = 0
      }

      if (curRun == k) {
        numBouquets++
        curRun = 0
      }
    }
    return numBouquets >= m
  }
}
