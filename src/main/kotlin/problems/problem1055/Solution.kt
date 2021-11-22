package problems.problem1055

class Solution {
  fun shortestWay(source: String, target: String): Int {
    val validCharacters = mutableSetOf<Char>()
    for (c in source) {
      validCharacters.add(c)
    }

    for (c in target) {
      if (!validCharacters.contains(c)) {
        return -1
      }
    }
    var targetIndex = 0
    var sourceIndex = 0
    var numSubSequences = 1
    while (targetIndex < target.length) {
      if (sourceIndex >= source.length) {
        sourceIndex = 0
        numSubSequences++
      }
      while (source[sourceIndex] != target[targetIndex]) {
        if (sourceIndex == source.length - 1) {
          sourceIndex = 0
          numSubSequences++
        }
        else {
          sourceIndex++
        }
      }
      targetIndex++
      sourceIndex++
    }
    return numSubSequences
  }
}

fun main(args: Array<String>) {
  println(Solution().shortestWay("abc","abcbc"))
  println(Solution().shortestWay("aaaaa","aaaaaaaaaaaaa"))
  println(Solution().shortestWay("xyz","xzyxz"))
}