package problems.problem3527

class Solution {
  fun findCommonResponse(responses: List<List<String>>): String {
    val frequencyMap = mutableMapOf<String, Int>()
    for (responsesDay in responses) {
      for (response in responsesDay.toSet()) {
        frequencyMap.merge(response, 1) { cur, acc -> cur + acc }
      }
    }

    return frequencyMap.entries.maxWithOrNull(
      compareBy<Map.Entry<String, Int>>({ it.value }).thenByDescending { it.key }
    )!!.key
  }
}