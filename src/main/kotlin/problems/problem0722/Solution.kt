package problems.problem0722

class Solution {
  fun removeComments(source: Array<String>): List<String> {
    var isBlockComment = false
    var prevCharWasStar = false
    val adjustedCode = mutableListOf<String>()
    var adjustedLine = mutableListOf<Char>()

    for (i in source.indices) {
      val line = source[i]
      for (j in line.indices) {

        if (isBlockComment) {
          if (line[j] == '/' && prevCharWasStar) {
            isBlockComment = false
            prevCharWasStar = false
          }
          else {
            prevCharWasStar = line.getOrNull(j) == '*'
          }
        }
        else if (line[j] == '*' && adjustedLine.lastOrNull() == '/') {
          adjustedLine.removeLastOrNull()
          isBlockComment = true
        }
        else if (line[j] == '/' && adjustedLine.lastOrNull() == '/') {
          adjustedLine.removeLastOrNull()
          break
        }
        else {
          adjustedLine.add(line[j])
        }
      }

      if (!isBlockComment && adjustedLine.isNotEmpty()) {
        adjustedCode.add(adjustedLine.joinToString(""))
        adjustedLine = mutableListOf<Char>()
      }
      prevCharWasStar = false
    }
    return adjustedCode
  }
}