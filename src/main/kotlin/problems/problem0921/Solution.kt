package problems.problem0921

class Solution {
  fun minAddToMakeValid(s: String): Int {
    var numLeftBrackets = 0
    var minAdds = 0
    for (i in s.indices) {
      if (s[i] == ')') {
        if (numLeftBrackets > 0) {
          numLeftBrackets--
        }
        else {
          minAdds++
        }
      }
      else {
        numLeftBrackets++
      }
    }
    minAdds += numLeftBrackets
    return minAdds
  }
}