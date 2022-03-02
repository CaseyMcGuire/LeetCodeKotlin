package problems.problem0761

class Solution {
  fun minTrioDegree(n: Int, edges: Array<IntArray>): Int {
    val graph = createGraph(n, edges)
    var minConnections: Int? = null


    for (i in 1..n) {
      for (j in i + 1..n) {
        for (k in j + 1..n) {
          val iNeighbors = graph[i]!!
          val jNeighbors = graph[j]!!
          val kNeighbors = graph[k]!!
          val iToJ = iNeighbors.contains(j)
          val iToK = iNeighbors.contains(k)
          val jToK = jNeighbors.contains(k)
          if (iToJ && iToK && jToK) {
            val connections = iNeighbors.size + jNeighbors.size + kNeighbors.size - 6
            if (minConnections == null || minConnections > connections) {
              minConnections = connections
            }
          }

        }
      }
    }
    return minConnections ?: -1
  }

  private fun createGraph(n: Int, edges: Array<IntArray>): Map<Int, MutableSet<Int>> {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 1..n) {
      graph[i] = mutableSetOf()
    }

    for (edge in edges) {
      val first = edge[0]
      val second = edge[1]
      graph[first]!!.add(second)
      graph[second]!!.add(first)
    }

    return graph
  }
}