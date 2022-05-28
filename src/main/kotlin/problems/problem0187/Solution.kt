package problems.problem0187

class Solution {
  fun findRepeatedDnaSequences(s: String): List<String> {
    val repeatedSequences = mutableSetOf<String>()
    val sequences = mutableSetOf<String>()
    for (i in 0 until s.length - 10 + 1) {
      val substring = s.substring(i, i + 10)
      if (!sequences.add(substring)) {
        repeatedSequences.add(substring)
      }
    }
    return repeatedSequences.toList()
  }
}