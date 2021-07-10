package problems.problem0049

class Solution {
  fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val groupedAnagrams = mutableMapOf<String, List<String>>()
    strs.forEach {
      groupedAnagrams.merge(
        it.toList()
          .sorted()
          .joinToString(""),
        mutableListOf(it)) { first, second ->
        first + second
      }
    }
    return groupedAnagrams.values.toList()
  }
}