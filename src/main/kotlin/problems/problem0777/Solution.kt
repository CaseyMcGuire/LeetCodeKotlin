package problems.problem0777

class Solution {
  fun canTransform(start: String, result: String): Boolean {
    val startList = start.toMutableList()

    for (i in startList.indices) {
      if (startList[i] == 'R' && result[i] == 'X') {
        var j = i
        while (j < startList.size && startList[j] == 'R') {
          j++
        }

        if (startList.getOrNull(j) != 'X') {
          return false
        }

        startList[i] = 'X'
        startList[j] = 'R'
      }
      else if (startList[i] == 'X' && result[i] == 'L') {
        var j = i
        while (j < startList.size && startList[j] == 'X') {
          j++
        }

        if (startList.getOrNull(j) != 'L') {
          return false
        }

        startList[i] = 'L'
        startList[j] = 'X'
      }
      else if (startList[i] != result[i]) {
        return false
      }
    }

    return true
  }
}