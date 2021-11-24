package problems.problem0311

class Solution {
  fun multiply(mat1: Array<IntArray>, mat2: Array<IntArray>): Array<IntArray> {
    val answer = Array(mat1.size) { IntArray(mat2[0].size) }
    val size = answer.size * answer[0].size
    var row = 0
    var column = 0
    var index = 0
    for (rowIndex in mat1.indices) {
      for (columnIndex in mat2[0].indices) {
        var sum = 0
        for (i in 0 until mat1[0].size) {
          sum += mat1[rowIndex][i] * mat2[i][columnIndex]
        }
        answer[row][column] = sum
        column++
        if (column == answer[0].size) {
          row++
          column = 0
        }
      }
    }
    return answer
  }
}