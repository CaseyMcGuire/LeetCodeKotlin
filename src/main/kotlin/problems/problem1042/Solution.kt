package problems.problem1042

class Solution {
  fun gardenNoAdj(n: Int, paths: Array<IntArray>): IntArray {
    val graph = createGraph(n, paths)
    val visited = mutableMapOf<Int, Int>()
    fun dfs(node: Int): Boolean {
      if (visited.contains(node)) {
        return true
      }
      val neighbors = graph[node]!!
      if (neighbors.isEmpty()) {
        visited[node] = 1
        return true
      }
      val possibleFlowers = mutableSetOf(1, 2, 3, 4)

      for (neighbor in neighbors) {
        val flower = visited[neighbor]
        if (flower != null) {
          possibleFlowers.remove(flower)
        }
      }

      if (possibleFlowers.isEmpty()) {
        return false
      }

      for (flower in possibleFlowers) {
        visited[node] = flower
        var found = true
        for (neighbor in neighbors) {
          found = found && dfs(neighbor)
        }
        if (found) {
          return true
        }
        visited.remove(node)
      }
      return false
    }
    for (i in 1..n) {
      dfs(i)
    }
    val toReturn = IntArray(n)
    for (entry in visited.entries) {
      toReturn[entry.key - 1] = entry.value
    }
    return toReturn
  }

  private fun createGraph(n: Int, paths: Array<IntArray>): MutableMap<Int, MutableSet<Int>> {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 1..n) {
      graph[i] = mutableSetOf()
    }

    for (path in paths) {
      val first = path[0]
      val second = path[1]
      graph[first]!!.add(second)
      graph[second]!!.add(first)
    }
    return graph
  }
}