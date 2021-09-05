package problems.problem0133

class Solution {
  fun cloneGraph(node: Node?): Node? {
    if (node == null) {
      return null
    }

    val visited = mutableMapOf<Int, Node>()
    fun visit(clonedNode: Node, nonClonedNode: Node) {
      // already visited this node somewhere else
      if (visited.containsKey(clonedNode.`val`)) {
        return
      }
      visited[clonedNode.`val`] = clonedNode
      for (neighbor in nonClonedNode.neighbors) {
        if (neighbor == null) {
          continue
        }
        val visitedClonedNeighbor = visited[neighbor.`val`]
        if (visitedClonedNeighbor != null) {
          clonedNode.neighbors.add(visitedClonedNeighbor)
        }
        else {
          val clonedNeighbor = Node(neighbor.`val`)
          clonedNode.neighbors.add(clonedNeighbor)
          visit(clonedNeighbor, neighbor)
        }
      }
    }
    val clonedRoot = Node(node.`val`)
    visit(clonedRoot, node)
    return clonedRoot
  }
}

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}