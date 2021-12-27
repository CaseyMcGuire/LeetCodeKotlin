package problems.problem1314

class Solution {
  fun matrixBlockSum(mat: Array<IntArray>, k: Int): Array<IntArray> {
    val sumMatrix = getSumMatrix(mat)
    val matrix = Array<IntArray>(mat.size) { IntArray(mat[0].size) }
    for (i in mat.indices) {
      for (j in mat[i].indices) {
        val bounds = getBounds(sumMatrix, i, j, k)
        var sum = 0
        for (k in bounds.top..bounds.bottom) {
          val rowSum = sumMatrix[k][bounds.right]
          val outsideRowSum = if (bounds.left == 0) 0 else sumMatrix[k][bounds.left - 1]
          sum += rowSum - outsideRowSum
        }
        matrix[i][j] = sum
      }
    }
    return matrix
  }

  private fun getBounds(mat: List<List<Int>>, i: Int, j: Int, k: Int): Bounds {
    val left = constrainNum(j - k, 0, mat[0].size - 1)
    val right = constrainNum(j + k, 0, mat[0].size - 1)
    val top = constrainNum(i - k, 0, mat.size - 1)
    val bottom = constrainNum(i + k, 0, mat.size - 1)
    return Bounds(left, right, top, bottom)
  }

  private fun constrainNum(num: Int, min: Int, max: Int): Int {
    if (num < min) {
      return min
    }
    else if (num > max) {
      return max
    }
    return num
  }

  private fun getSumMatrix(mat: Array<IntArray>): List<List<Int>> {
    val matrix = mutableListOf<List<Int>>()
    for (i in mat.indices) {
      val curRow = mutableListOf<Int>()
      for (j in mat[i].indices) {
        if (j == 0) {
          curRow.add(mat[i][j])
        }
        else {
          curRow.add(curRow[j - 1] + mat[i][j])
        }
      }
      matrix.add(curRow)
    }
    return matrix
  }

}

data class Bounds(val left: Int, val right: Int, val top: Int, val bottom: Int)