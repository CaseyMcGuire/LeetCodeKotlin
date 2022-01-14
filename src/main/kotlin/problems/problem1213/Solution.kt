package problems.problem1213

class Solution {
  fun arraysIntersection(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
    return (arr1.toSet() intersect arr2.toSet() intersect arr3.toSet()).toList()
  }
}