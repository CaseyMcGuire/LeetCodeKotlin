package problems.problem0171

class Solution {
  fun titleToNumber(columnTitle: String): Int {
    val mappings = getMappings()
    var sum = 0
    for (i in columnTitle.indices) {
      val index = columnTitle.length - 1 - i
      val char = columnTitle[index]
      val num = mappings[char]!!
      sum += Math.pow(26.0, i.toDouble()).toInt() * num
    }
    return sum
  }

  fun getMappings(): Map<Char, Int> {
    val chars = ('A'..'Z').toList()
    val mappings = mutableMapOf<Char, Int>()
    for (i in chars.indices) {
      mappings[chars[i]] = i + 1
    }
    return mappings
  }
}