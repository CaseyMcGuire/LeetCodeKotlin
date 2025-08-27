package problems.problem0825

class Solution {
  fun numFriendRequests(ages: IntArray): Int {
    ages.sort()
    var total = 0
    for (i in ages.indices) {
      total += ages.getNumFriendRequestsForIndex(i)
    }
    return total
  }

  private fun IntArray.getNumFriendRequestsForIndex(i: Int): Int {
    val curAge = this[i]
    var max = curAge

    val min = (curAge / 2 + 7) + 1
    if (min > max) {
      return 0
    }
    val leftIndex = this.getBoundary(min, max, true)
      ?: return 0
    val rightIndex = this.getBoundary(min, max, false)
      ?: return 0

    if (curAge in min..max) {
      return rightIndex - leftIndex
    }
    else {
      return rightIndex - leftIndex + 1
    }
  }

  private fun IntArray.getBoundary(min: Int, max: Int, findLeft: Boolean): Int? {
    var low = 0
    var high = this.size - 1
    while (low <= high) {
      val middle = ((low.toLong() + high.toLong()) / 2L).toInt()
      val age = this[middle]
      if (age !in min..max) {
        if (age < min) {
          low = middle + 1
        }
        else {
          high = middle - 1
        }
      }
      else {
        if (findLeft) {
          if (this.getOrNull(middle - 1) in min..max) {
            high = middle - 1
          }
          else {
            return middle
          }
        }
        else {
          if (this.getOrNull(middle + 1) in min..max) {
            low = middle + 1
          }
          else {
            return middle
          }
        }
      }
    }
    return null
  }


}