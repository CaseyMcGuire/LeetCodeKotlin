package problems.problem0399

class Solution {
  fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
    val graph = mutableMapOf<String, MutableMap<String, Double>>()
    for (i in equations.indices) {
      val equation = equations[i]
      graph.add(equation[0], equation[1], values[i])
      graph.add(equation[1], equation[0], 1.0 / values[i])
      graph.add(equation[0], equation[0], 1.0)
      graph.add(equation[1], equation[1], 1.0)
    }

    fun dfs(cur: String, target: String, visited: MutableSet<String>): Double {
      if (cur == target) {
        return 1.0
      }

      if (!visited.add(cur)) {
        return -1.0
      }

      val neighbors = graph[cur]
      if (neighbors == null) {
        return -1.0
      }

      for (neighbor in neighbors.entries) {
        val value = dfs(neighbor.key, target, visited)
        if (value != -1.0) {
          return neighbor.value * value
        }
      }
      return -1.0
    }

    val answers = mutableListOf<Double>()
    for (query in queries) {
      if (!graph.containsKey(query[0]) || !graph.containsKey(query[1])) {
        answers.add(-1.0)
      }
      else {
        answers.add(dfs(query[0], query[1], mutableSetOf()))
      }
    }
    return answers.toDoubleArray()
  }

  private fun MutableMap<String, MutableMap<String, Double>>.add(first: String, second: String, value: Double) {
    val nested = this[first] ?: mutableMapOf()
    nested[second] = value
    this[first] = nested
  }


}