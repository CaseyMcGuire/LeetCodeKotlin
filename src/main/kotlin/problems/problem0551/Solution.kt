package problems.problem0551

class Solution {
  fun checkRecord(s: String): Boolean {
    var numAbsences = 0
    var consecutiveLateDays = 0
    for (c in s) {
      if (c == 'A') {
        if (numAbsences == 1) {
          return false
        }
        else {
          numAbsences++
        }
      }
      if (c != 'L') {
        consecutiveLateDays = 0
      }
      else {
        if (consecutiveLateDays == 2) {
          return false
        }
        consecutiveLateDays++
      }
    }
    return true
  }
}