package problems.problem0165

class Solution {
  fun compareVersion(version1: String, version2: String): Int {
    val firstSplit = version1.split(".").toMutableList()
    val secondSplit = version2.split(".").toMutableList()

    while (firstSplit.size < secondSplit.size) {
      firstSplit.add("0")
    }

    while (secondSplit.size < firstSplit.size) {
      secondSplit.add("0")
    }

    for (i in firstSplit.indices) {
      val firstNum = firstSplit[i].toInt()
      val secondNum = secondSplit[i].toInt()
      if (firstNum > secondNum) {
        return 1
      }
      else if (firstNum < secondNum) {
        return -1
      }
    }

    return 0
  }
}