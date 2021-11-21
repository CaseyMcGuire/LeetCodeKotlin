package problems.problem0737

class Solution {
  fun areSentencesSimilarTwo(sentence1: Array<String>, sentence2: Array<String>, similarPairs: List<List<String>>): Boolean {
    if (sentence1.size != sentence2.size) {
      return false
    }

    val graph = getGraph(sentence1, sentence2, similarPairs)
    for (i in sentence1.indices) {
      val first = sentence1[i]
      val second = sentence2[i]
      if (!isSimilar(graph, first, second)) {
        return false
      }
    }
    return true
  }

  private fun isSimilar(graph: Map<String, MutableSet<String>>, start: String, end: String): Boolean {

    fun recurse(current: String, path: MutableSet<String>): Boolean {
      if (current == end) {
        return true
      }
      val edges = graph[current]
      if (edges == null) {
        return false
      }
      for (edge in edges) {
        if (!path.contains(edge)) {
          path.add(edge)
          val meets = recurse(edge, path)
          path.remove(edge)
          if (meets) {
            return true
          }
        }
      }
      return false
    }
    return recurse(start, mutableSetOf())
  }

  private fun getGraph(sentence1: Array<String>, sentence2: Array<String>, pairs: List<List<String>>): Map<String, MutableSet<String>> {
    val map = mutableMapOf<String, MutableSet<String>>()
    for (s in sentence1) {
      val set = map.getOrDefault(s, mutableSetOf())
      set.add(s)
      map[s] = set
    }

    for (s in sentence2) {
      val set = map.getOrDefault(s, mutableSetOf())
      set.add(s)
      map[s] = set
    }

    for (pair in pairs) {
      val first = pair[0]
      val second = pair[1]
      val firstSet = map.getOrDefault(first, mutableSetOf())
      firstSet.add(first)
      firstSet.add(second)
      map[first] = firstSet

      val secondSet = map.getOrDefault(second, mutableSetOf())
      secondSet.add(second)
      secondSet.add(first)
      map[second] = secondSet
    }
    return map
  }
}