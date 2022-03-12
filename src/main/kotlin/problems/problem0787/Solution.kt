package problems.problem0787

import java.util.*

class Solution {
  fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
    val graph = createGraph(n, flights)
    val pathToShortest = mutableMapOf<CacheKey, Int>()
    val nodesInPath = mutableSetOf<Int>()
    val maxNodesInPath = k + 1
    fun dfs(node: Int, prev: Int): Int {
      if (nodesInPath.size > maxNodesInPath) {
        return -1
      }
      if (node == dst) {
        return 0
      }
      if (!nodesInPath.add(node)) {
        return -1
      }
      val key = CacheKey(node, prev, nodesInPath.size)
      val cached = pathToShortest[key]
      if (cached != null) {
        nodesInPath.remove(node)
        return cached
      }



      val neighbors = graph[node] ?: mutableListOf()
      var min = -1
      for (neighbor in neighbors) {
        val shortest = dfs(neighbor.dest, node)
        if (shortest != -1) {
          val pathLength = shortest + neighbor.weight
          if (min == -1 || pathLength < min) {
            min = pathLength
          }
        }
      }

      pathToShortest[key] = min
      nodesInPath.remove(node)
      return min
    }
    return dfs(src, -1)
  }

  private fun createGraph(n: Int, flights: Array<IntArray>): Map<Int, MutableList<Edge>> {
    val graph = mutableMapOf<Int, MutableList<Edge>>()
    for (flight in flights) {
      val from = flight[0]
      val to = flight[1]
      val price = flight[2]

      val existingEdges = graph[from] ?: mutableListOf()
      existingEdges.add(Edge(to, price))
      graph[from] = existingEdges
    }
    return graph
  }
}

data class Edge(val dest: Int, val weight: Int)
data class CacheKey(val src: Int, val dest: Int, val numNodes: Int)
