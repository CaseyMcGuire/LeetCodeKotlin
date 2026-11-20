package problems.problem0684

class Solution {
  fun findRedundantConnection(edges: Array<IntArray>): IntArray {
    val graph = createGraph(edges)
    val edgeSetToOrder = mutableMapOf<Set<Int>, Int>()
    for (i in edges.indices) {
      val edge = edges[i]
      edgeSetToOrder[setOf(edge[0], edge[1])] = i
    }
    val path = mutableMapOf<Int, Int>()

    fun traverse(to: Int, from: Int?): IntArray? {
      if (path.containsKey(to)) {
        val thisEdge = setOf(to, from!!)
        var latestOrder = edgeSetToOrder[thisEdge]!!
        var nextNode = path[to]!!
        var nextEdge = setOf(to, nextNode)
        while (nextEdge != thisEdge) {
          latestOrder = Math.max(edgeSetToOrder[nextEdge]!!, latestOrder)
          nextEdge = setOf(nextNode, path[nextNode]!!)
          nextNode = path[nextNode]!!
        }
        return edges[latestOrder]
      }

      val neighbors = graph[to]!!.filter { it != from }
      for (neighbor in neighbors) {
        path[to] = neighbor
        val result = traverse(neighbor, to)
        if (result != null) {
          return result
        }
        path.remove(to)
      }
      return null
    }

    return traverse(edges.last()[0], null)!!
  }

  private fun createGraph(edges: Array<IntArray>): Map<Int, Set<Int>> {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    for (edge in edges) {
      val (first, second) = edge
      graph.getOrPut(first) { mutableSetOf() }.add(second)
      graph.getOrPut(second) { mutableSetOf() }.add(first)
    }
    return graph
  }
}