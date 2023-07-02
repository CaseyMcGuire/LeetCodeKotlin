package problems.problem2215

class Solution {
  fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
    val nums1Diff = (nums1.toSet() subtract nums2.toSet()).toList()
    val nums2Diff = (nums2.toSet() subtract nums1.toSet()).toList()
    return listOf(nums1Diff, nums2Diff)
  }
}