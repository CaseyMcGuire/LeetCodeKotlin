package problems.problem0088

class Solution {
  fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var index = nums1.size - 1
    var cur1 = m - 1
    var cur2 = n - 1
    while (index >= 0) {
      if (cur1 < 0) {
        nums1[index] = nums2[cur2]
        cur2--
      }
      else if (cur2 < 0) {
        nums1[index] = nums1[cur1]
        cur1--
      }
      else if (nums1[cur1] < nums2[cur2]) {
        nums1[index] = nums2[cur2]
        cur2--
      }
      else {
        nums1[index] = nums1[cur1]
        cur1--
      }
      index--
    }
  }
}