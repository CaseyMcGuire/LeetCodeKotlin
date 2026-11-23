package problems.problem1129

class Solution {
  fun shortestAlternatingPaths(
    n: Int,
    redEdges: Array<IntArray>,
    blueEdges: Array<IntArray>
  ): IntArray {
    val redGraph = createGraph(redEdges)
    val blueGraph = createGraph(blueEdges)

    val minDistance = mutableMapOf<Node, Int>()

    val queue = ArrayDeque<Path>()
    val bluePath = Path(0, Color.BLUE, 0)
    val redPath = Path(0, Color.RED, 0)
    queue.addLast(bluePath)
    queue.addLast(redPath)
    minDistance[bluePath.getNode()] = 0
    minDistance[redPath.getNode()] = 0
    val minDistancesPerNode = IntArray(n) { -1 }

    while (queue.isNotEmpty()) {
      val next = queue.removeFirst()
      if (minDistancesPerNode[next.nodeId] == -1) {
        minDistancesPerNode[next.nodeId] = next.length
      }
      val node = next.getNode()
      var curMinDistance = minDistance[node]!!

      val neighbors = when (next.color) {
        Color.RED -> redGraph[next.nodeId]
        Color.BLUE -> blueGraph[next.nodeId]
      } ?: emptyList()

      for (neighbor in neighbors) {
        val neighborPath = Path(neighbor, node.color.getOpposite(), next.length + 1)
        val neighborNode = neighborPath.getNode()
        val neighborMinDistance = minDistance[neighborNode]
        if (neighborNode in minDistance) {
          continue
        }
        minDistance[neighborNode] = neighborPath.length
        queue.addLast(neighborPath)
      }
    }

    return minDistancesPerNode
  }

  private fun createGraph(edges: Array<IntArray>): Map<Int, Set<Int>> {
    val graph = mutableMapOf<Int, MutableSet<Int>>()

    for (edge in edges) {
      graph.getOrPut(edge[0]) { mutableSetOf() }.add(edge[1])
    }

    return graph
  }

  private data class Node(val id: Int, val color: Color)
  private data class Path(val nodeId: Int, val color: Color, val length: Int) {
    fun getNode(): Node {
      return Node(nodeId, color)
    }
  }

  private enum class Color {
    BLUE,
    RED;

    fun getOpposite(): Color = when (this) {
      BLUE -> RED
      RED -> BLUE
    }
  }
}