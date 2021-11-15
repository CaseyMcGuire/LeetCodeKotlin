package problems.problem0297

import datastructures.TreeNode
import java.util.*

class Codec() {

  fun serialize(root: TreeNode?): String {
    if (root == null) {
      return ""
    }
    val stringList = mutableListOf<String>()
    val queue = LinkedList<TreeNode?>()
    queue.addLast(root)
    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      if (cur == null) {
        stringList.add("#")
        continue
      }
      val value = cur.`val`
      queue.addLast(cur.left)
      queue.addLast(cur.right)
      stringList.add("$value")
    }

    return stringList.joinToString(",")
  }


  fun deserialize(data: String): TreeNode? {
    if (data == "") {
      return null
    }

    val nodes = LinkedList(data.split(","))
    val root = TreeNode(nodes.removeFirst().toInt())
    var currentLevel = LinkedList(mutableListOf(root))
    var nextLevel = LinkedList(mutableListOf<TreeNode>())
    while (nodes.isNotEmpty()) {
      val cur = currentLevel.removeFirst()
      val left = nodes.removeFirst()
      if (left != "#") {
        val leftNode = TreeNode(left.toInt())
        cur.left = leftNode
        nextLevel.addLast(leftNode)
      }
      val right = nodes.removeFirst()
      if (right != "#") {
        val rightNode = TreeNode(right.toInt())
        cur.right = rightNode
        nextLevel.addLast(rightNode)
      }

      if (currentLevel.isEmpty()) {
        currentLevel = nextLevel
        nextLevel = LinkedList()
      }
    }
    return root
  }
}