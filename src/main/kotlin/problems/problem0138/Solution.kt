package problems.problem0138

class Solution {
  fun copyRandomList(node: Node?): Node? {
    if (node == null) {
      return null
    }
    val oldNodeToNewNode = HashMap<Node?, Node?>()
    var currentNodeInOldList = node
    var currentNodeInNewList: Node? = Node(node.`val`)
    val headOfNewList = currentNodeInNewList

    while (currentNodeInOldList != null) {
      oldNodeToNewNode[currentNodeInOldList] = currentNodeInNewList
      currentNodeInOldList = currentNodeInOldList.next
      if (currentNodeInOldList != null) {
        val newNextNode = Node(currentNodeInOldList.`val`)
        currentNodeInNewList!!.next = newNextNode
        currentNodeInNewList = newNextNode
      }
    }

    currentNodeInNewList = headOfNewList
    currentNodeInOldList = node
    while (currentNodeInOldList != null && currentNodeInNewList != null) {
      if (currentNodeInOldList.random != null) {
        val randomNewNode = oldNodeToNewNode[currentNodeInOldList.random]
        currentNodeInNewList.random = randomNewNode
      }
      currentNodeInNewList = currentNodeInNewList.next
      currentNodeInOldList = currentNodeInOldList.next
    }
    return headOfNewList
  }
}

class Node(var `val`: Int) {
  var next: Node? = null
  var random: Node? = null
}