package problems.problem1078

class Solution {
  fun findOcurrences(text: String, first: String, second: String): Array<String> {
    val splitText = text.split(" ")
    if (splitText.size <= 2) {
      return emptyArray()
    }
    var firstIndex = 0
    var secondIndex = 1
    val thirds = mutableListOf<String>()
    while (secondIndex < splitText.size - 1) {
      if (splitText[firstIndex] == first && splitText[secondIndex] == second) {
        thirds.add(splitText[secondIndex + 1])
      }
      firstIndex++
      secondIndex++
    }
    return thirds.toTypedArray()
  }
}