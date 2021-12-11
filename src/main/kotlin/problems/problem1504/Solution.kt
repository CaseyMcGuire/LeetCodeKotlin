package problems.problem1504

class Solution {
  fun numSubmat(mat: Array<IntArray>): Int {
    var count = 0
    for (i in mat.indices) {
      for (j in mat[i].indices) {
        count += numMatricesAtCoordinate(mat, i, j)
      }
    }
    return count
  }

  fun numMatricesAtCoordinate(mat: Array<IntArray>, row: Int, column: Int): Int {
    var columnBoundary = mat[0].size
    var count = 0
    for (i in row until mat.size) {
      for (j in column until columnBoundary) {
        if (mat[i][j] == 0) {
          columnBoundary = j
          break
        }
        count++
      }
    }
    return count
  }
}