package problems.problem2493

import java.util.*

class Solution {
  fun magnificentSets(n: Int, edges: Array<IntArray>): Int {
    val graph = createGraph(n, edges)
    val nodeToComponent = mutableMapOf<Int, Int>()

    fun findComponents(node: Int, component: Int) {
      val existing = nodeToComponent[node]
      if (existing != null) {
        return
      }
      nodeToComponent[node] = component
      for (neighbor in graph[node]!!) {
        findComponents(neighbor, component)
      }
    }

    fun bfs(startingNode: Int): Int  {
      val nodeToGroup = mutableMapOf<Int, Int>()

      val queue = LinkedList<NodeGroup>()
      queue.addLast(NodeGroup(startingNode, 1))
      var maxSoFar = 1
      nodeToGroup[startingNode] = 1

      while (queue.isNotEmpty()) {

        val elem = queue.removeFirst()
        val node = elem.node
        val group = elem.group
        maxSoFar = Math.max(maxSoFar, group)

        val neighbors = graph[node]!!

        for (neighbor in neighbors) {
          val neighborGroup = nodeToGroup[neighbor]
          if (neighborGroup == null) {
            val nextGroup = group + 1
            nodeToGroup[neighbor] = nextGroup
            queue.addLast(NodeGroup(neighbor, nextGroup))
          }
          else if (Math.abs(neighborGroup - group).toInt() != 1) {
            return -1
          }
        }
      }
      return maxSoFar
    }

    for (i in 1..n) {
      findComponents(i, i)
    }

    val maxPerComponent = mutableMapOf<Int, Int>()
    for (entry in nodeToComponent.entries) {
      val curMax = maxPerComponent[entry.value] ?: 0
      val max = bfs(entry.key)
      if (max == -1) {
        return -1
      }
      maxPerComponent[entry.value] = Math.max(curMax, max)
    }


    return maxPerComponent.values.sum()
  }

  private fun createGraph(n: Int, edges: Array<IntArray>): Map<Int, Set<Int>> {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 1..n) {
      graph[i] = mutableSetOf()
    }

    for (edge in edges) {
      val first = edge[0]
      val second = edge[1]
      val firstNeighbors = graph[first]!!
      val secondNeighbors = graph[second]!!
      firstNeighbors.add(second)
      secondNeighbors.add(first)
    }
    return graph
  }
}

data class NodeGroup(val node: Int, val group: Int)