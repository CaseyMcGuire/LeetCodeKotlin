package problems.problem0652

import datastructures.TreeNode

class Solution {
  fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
    if (root == null) {
      return emptyList()
    }
    val visited = mutableSetOf<SubTree>()
    val duplicates = mutableSetOf<SubTree>()
    fun recurse(tree: SubTree) {
      if (tree.root.right != null) {
        recurse(SubTree(tree.root.right!!))
      }
      if (tree.root.left != null) {
        recurse(SubTree(tree.root.left!!))
      }
      if (!visited.add(tree)) {
        duplicates.add(tree)
      }
    }
    recurse(SubTree(root))
    return duplicates.map {it.root}.toList()
  }
}

class SubTree(val root: TreeNode) {

  private var cachedHashCode: Int? = null

  override fun hashCode(): Int {
    if (cachedHashCode != null) {
      return cachedHashCode as Int
    }
    var result = 17
    result = 31 * result + root.`val`
    result = 31 * result + (if (root.left != null) SubTree(root.left!!).hashCode() else 0)
    result = 31 * result + (if (root.right != null) SubTree(root.right!!).hashCode() else 0)
    cachedHashCode = result
    return result
  }

  override fun equals(other: Any?): Boolean {
    if (other !is SubTree) {
      return false
    }
    if (other.root.`val` != root.`val`) {
      return false
    }
    if (other.root.left == null && root.left != null ||
      other.root.left != null && root.left == null ||
      other.root.right == null && root.right != null ||
      other.root.right != null && root.right == null) {
      return false
    }
    if (other.root.left != null && root.left != null) {
      val otherLeftSubtree = SubTree(other.root.left!!)
      val thisLeftSubtree = SubTree(root.left!!)
      if (otherLeftSubtree != thisLeftSubtree) {
        return false
      }
    }
    if (other.root.right != null && root.right != null) {
      val otherRightSubTree = SubTree(other.root.right!!)
      val thisRightSubtree = SubTree(root.right!!)
      if (otherRightSubTree != thisRightSubtree) {
        return false
      }
    }
    return true
  }
}