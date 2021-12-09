package problems.problem0547

class Solution {
  fun findCircleNum(isConnected: Array<IntArray>): Int {
    val graph = createGraph(isConnected)

    val visited = mutableSetOf<Int>()
    fun dfs(curNode: Int) {
      if (!visited.add(curNode)) {
        return
      }

      val neighbors = graph[curNode]!!
      for (neighbor in neighbors) {
        dfs(neighbor)
      }
    }

    var numProvinces = 0
    for (i in isConnected.indices) {
      if (visited.contains(i)) {
        continue
      }
      dfs(i)
      numProvinces++
    }
    return numProvinces
  }

  private fun createGraph(connections: Array<IntArray>): MutableMap<Int, MutableSet<Int>> {
    val graph = mutableMapOf<Int, MutableSet<Int>>()

    for (i in connections.indices) {
      graph[i] = mutableSetOf()
    }

    for (i in connections.indices) {
      for (j in connections[i].indices) {
        val connected = connections[i][j] == 1
        if (connected) {
          graph[i]!!.add(j)
          graph[j]!!.add(i)
        }
      }
    }
    return graph
  }
}