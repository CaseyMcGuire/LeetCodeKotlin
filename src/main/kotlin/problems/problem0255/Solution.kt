package problems.problem0255

class Solution {
  fun verifyPreorder(preorder: IntArray): Boolean {
    var isPreorder = true
    fun recurse(index: Int, end: Int, min: Int?, max: Int?) {

      val num = preorder[index]
      if (min != null && num < min) {
        isPreorder = false
        return
      }
      if (max != null && num > max) {
        isPreorder = false
        return
      }
      if (index + 1 >= end) {
        return
      }
      val greaterIndex = preorder.greaterThanIndex(preorder[index], index + 1, end)
      if (greaterIndex != null) {
        recurse(greaterIndex, end, num, max)
      }
      if (index + 1 != greaterIndex) {
        recurse(index + 1, greaterIndex ?: end, min, num)
      }


    }
    recurse(0, preorder.size, null, null)
    return isPreorder
  }

  fun IntArray.greaterThanIndex(num: Int, start: Int, end: Int): Int? {
    for (i in start until end) {
      if (this[i] > num) {
        return i
      }
    }
    return null
  }
}

