package problems.problem1490

class Solution {
  fun cloneTree(root: Node?): Node? {
    if (root == null) {
      return null
    }
    val clonedNode = Node(root.`val`)

    fun recurse(node: Node, clone: Node) {
      val clonedChildren = mutableListOf<Node>()
      for (child in node.children) {
        if (child == null) {
          continue
        }
        val clonedChild = Node(child.`val`)
        clonedChildren.add(clonedChild)
        recurse(child, clonedChild)
      }
      clone.children = clonedChildren
    }
    recurse(root, clonedNode)
    return clonedNode
  }
}

class Node(var `val`: Int) {
  var children: List<Node?> = listOf()
}