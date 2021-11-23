package problems.problem1059

class Solution {
  fun leadsToDestination(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 0 until n) {
      graph[i] = mutableSetOf()
    }

    for (edge in edges) {
      val first = edge[0]
      val second = edge[1]
      val set = graph[first]
      set!!.add(second)
    }

    // if destination has any outgoing nodes, it's not a terminal node
    if (graph[destination]!!.isNotEmpty()) {
      return false
    }

    fun dfs(currentNode: Int, path: MutableSet<Int>): Boolean {
      // we've already visited this node, so destination is not a terminal node
      if (path.contains(currentNode)) {
        return false
      }

      if (currentNode == destination) {
        return true
      }

      val edges = graph[currentNode]!!
      // we've reached a terminal node that's not destination, therefore all paths
      // don't lead to destination
      if (edges.isEmpty()) {
        return false
      }
      path.add(currentNode)
      var allLeadToDestination = true
      for (edge in edges) {
        allLeadToDestination = allLeadToDestination && dfs(edge, path)
      }
      path.remove(currentNode)
      return allLeadToDestination
    }
    return dfs(source, mutableSetOf<Int>())
  }
}