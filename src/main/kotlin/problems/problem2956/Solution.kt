package problems.problem2956

class Solution {
  fun findIntersectionValues(nums1: IntArray, nums2: IntArray): IntArray {
    val set1 = nums1.toSet()
    val set2 = nums2.toSet()
    val num1Matches = nums1.filter { set2.contains(it) }.size
    val num2Matches = nums2.filter { set1.contains(it) }.size
    return intArrayOf(num1Matches, num2Matches)
  }
}