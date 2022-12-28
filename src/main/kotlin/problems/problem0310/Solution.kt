package problems.problem0310

class Solution {
  fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
    var leaves = mutableSetOf<Int>()
    val graph = createGraph(n, edges)
    for (entry in graph.entries) {
      if (entry.value.size <= 1) {
        leaves.add(entry.key)
      }
    }

    while (leaves.size != graph.size) {
      val nextLeaves = mutableSetOf<Int>()
      for (leaf in leaves) {
        val connectedNode = graph[leaf]!!.first()
        graph.remove(leaf)
        val otherConnectedNodes = graph[connectedNode]!!
        otherConnectedNodes.remove(leaf)
        if (otherConnectedNodes.size <= 1) {
          nextLeaves.add(connectedNode)
        }
      }
      leaves = nextLeaves
    }

    return leaves.toList()
  }

  fun createGraph(n: Int, edges: Array<IntArray>): MutableMap<Int, MutableSet<Int>> {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 0 until n) {
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