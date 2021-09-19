package problems.problem0323

class Solution {
  fun countComponents(n: Int, edges: Array<IntArray>): Int {
    val nodes = mutableMapOf<Int, Node>()
    for (i in 0 until n) {
      nodes[i] = Node(i)
    }
    for (edge in edges) {
      val first = edge[0]
      val second = edge[1]
      val firstNode = nodes[first]
      val secondNode = nodes[second]
      firstNode!!.edges.add(second)
      secondNode!!.edges.add(first)
    }

    var numConnectedComponents = 0
    val visited = mutableSetOf<Int>()
    fun recurse(nodeNum: Int) {
      val node = nodes[nodeNum]!!
      if (!visited.add(node.num)) {
        return
      }
      for (edge in node.edges) {
        recurse(edge)
      }
    }
    for (i in 0 until n) {
      if (!visited.contains(i)) {
        numConnectedComponents++
        recurse(i)
      }
    }
    return numConnectedComponents

  }
}

data class Node(val num: Int, val edges: MutableList<Int> = mutableListOf())