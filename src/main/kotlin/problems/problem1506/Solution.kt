package problems.problem1506

 class Node(var `val`: Int) {
   var children: List<Node?> = listOf()
 }

class Solution {
  fun findRoot(tree: List<Node>): Node? {
    val nodeToParent = mutableMapOf<Node, Node>()
    for (node in tree) {
      for (child in node.children) {
        if (child == null) {
          continue
        }
        nodeToParent[child] = node
      }
    }
    for (node in tree) {
      val elem = nodeToParent[node]
      if (elem == null) {
        return node
      }
    }
    return null
  }
}