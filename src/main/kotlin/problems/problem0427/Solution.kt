package problems.problem0427

class Solution {
  fun construct(grid: Array<IntArray>): Node? {

    fun constructNode(i: Int, j: Int, size: Int): Node {

      val leafValue = getLeafValue(grid, i, j, size)
      val node = Node(leafValue == 1, leafValue >= 0)
      if (node.isLeaf || size == 1) {
        return node
      }

      val newSize = size / 2
      node.topLeft = constructNode(i, j, newSize)
      node.topRight = constructNode(i, j + newSize, newSize)
      node.bottomLeft = constructNode(i + newSize, j, newSize)
      node.bottomRight = constructNode(i + newSize, j + newSize, newSize)
      return node
    }

    return constructNode(0, 0, grid.size)
  }

  private fun getLeafValue(grid: Array<IntArray>, i: Int, j: Int, size: Int): Int {
    val initial = grid[i][j]
    for (k in i until i + size) {
      for (h in j until j + size) {
        if (grid[k][h] != initial) {
          return -1
        }
      }
    }
    return initial
  }

}

class Node(var `val`: Boolean, var isLeaf: Boolean) {
     var topLeft: Node? = null
     var topRight: Node? = null
     var bottomLeft: Node? = null
     var bottomRight: Node? = null
}