package problems.problem0919

import datastructures.TreeNode
import java.util.*

class CBTInserter(root: TreeNode?) {

  private var currentLevel = LinkedList<TreeNode>()
  private var nextLevel = LinkedList<TreeNode>()
  private var _root = root

  init {
    initializeData(root)
  }

  fun initializeData(root: TreeNode?) {
    if (root == null) {
      return
    }
    currentLevel.addFirst(root)
    while (true){
      var finished = false
      while (currentLevel.isNotEmpty()) {
        val node = currentLevel.removeFirst()
        val left = node.left
        if (left == null) {
          finished = true
        }
        else {
          nextLevel.addLast(left)
        }
        val right = node.right
        if (right == null) {
          finished = true
        }
        else {
          nextLevel.addLast(right)
        }
        if (finished) {
          currentLevel.addFirst(node)
          break
        }
      }
      if (finished) {
        break
      }
      else {
        currentLevel = nextLevel
        nextLevel = LinkedList<TreeNode>()
      }
    }
  }

  fun insert(`val`: Int): Int {
    if (_root == null) {
      _root = TreeNode(`val`)
      currentLevel.addFirst(_root)
      return `val`
    }

    val newNode = TreeNode(`val`)
    val currentNode = currentLevel.removeFirst()
    if (currentNode.left == null) {
      currentNode.left = newNode
      currentLevel.addFirst(currentNode)
    }
    else {
      currentNode.right = newNode
    }
    nextLevel.addLast(newNode)

    if (currentLevel.isEmpty()) {
      currentLevel = nextLevel
      nextLevel = LinkedList<TreeNode>()
    }

    return currentNode.`val`
  }

  fun get_root(): TreeNode? {
    return _root
  }

}