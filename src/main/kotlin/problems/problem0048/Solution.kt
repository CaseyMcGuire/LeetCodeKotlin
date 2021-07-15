package problems.problem0048

class Solution {
  fun rotate(matrix: Array<IntArray>): Unit {
    var currentStartIndex = 0
    var currentEndIndex = matrix.size - 1
    while (currentStartIndex < currentEndIndex) {
      val size = currentEndIndex - currentStartIndex
      for (i in 0 until size) {
        val top = Pair(currentStartIndex, currentStartIndex + i)
        val right = Pair(currentStartIndex + i, currentEndIndex)
        val bottom = Pair(currentEndIndex, currentEndIndex - i)
        val left = Pair(currentEndIndex - i, currentStartIndex)
        val temp = matrix[top.first][top.second]
        matrix[top.first][top.second] = matrix[left.first][left.second]
        matrix[left.first][left.second] = matrix[bottom.first][bottom.second]
        matrix[bottom.first][bottom.second] = matrix[right.first][right.second]
        matrix[right.first][right.second] = temp
      }
      currentStartIndex++
      currentEndIndex--
    }
  }
}

fun main(args: Array<String>) {
  Solution().rotate(arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6,7,8), intArrayOf(9,10,11,12), intArrayOf(13,14,15,16)));
}