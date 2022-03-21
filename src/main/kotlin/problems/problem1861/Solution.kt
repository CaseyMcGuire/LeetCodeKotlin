package problems.problem1861

class Solution {
  fun rotateTheBox(box: Array<CharArray>): Array<CharArray> {

    for (i in box.indices) {
      for (j in box[i].indices.reversed()) {
        if (box[i][j] == '#') {
          box[i][j] = '.'
          for (k in j until box[i].size) {
            if (k == box[i].size - 1 || box[i][k + 1] == '*' || box[i][k + 1] == '#') {
              box[i][k] = '#'
              break
            }
          }
        }
      }
    }

    val newBox = Array<CharArray>(box[0].size) { CharArray(box.size) }

    for (i in box.indices) {
      for (j in box[i].indices) {
        newBox[j][box.size - i - 1] = box[i][j]
      }
    }

    return newBox
  }
}