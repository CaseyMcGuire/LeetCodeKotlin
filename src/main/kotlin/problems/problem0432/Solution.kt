package problems.problem0432

import java.util.*

class AllOne() {

  private val head = Node(-1, "")
  private val tail = Node(-1, "")
  private val keyToFrequencyNode = mutableMapOf<String, Node>()

  init {
    head.next = tail
    tail.prev = head
  }

  fun inc(key: String) {
    val existingNode = keyToFrequencyNode[key]
    if (existingNode == null) {
      if (head.next?.count == 1) {
        head.next!!.add(key)
        keyToFrequencyNode[key] = head.next!!
      }
      else {
        val newNode = Node(1, key)
        keyToFrequencyNode[key] = newNode
        head.addAfter(newNode)
      }
    }
    else {
      val next = existingNode.next
      existingNode.remove(key)
      keyToFrequencyNode.remove(key)
      if (next != null && next.count == existingNode.count + 1) {
        next.add(key)
        keyToFrequencyNode[key] = next
      }
      else {
        val newNode = Node(existingNode.count + 1, key)
        existingNode.addAfter(newNode)
        keyToFrequencyNode[key] = newNode
      }

      if (existingNode.isEmpty()) {
        existingNode.detach()
      }
    }
  }

  fun dec(key: String) {
    val existingNode = keyToFrequencyNode[key]
      ?: return
    val prev = existingNode.prev
    existingNode.remove(key)
    keyToFrequencyNode.remove(key)
    if (existingNode.count > 1) {
      if (prev != null && prev.count == existingNode.count - 1) {
        keyToFrequencyNode[key] = prev
        prev.add(key)
      }
      else {
        val newNode = Node(existingNode.count - 1, key)
        keyToFrequencyNode[key] = newNode
        prev!!.addAfter(newNode)
      }
    }

    if (existingNode.isEmpty()) {
      existingNode.detach()
    }
  }

  fun getMaxKey(): String {
    return tail.prev?.getElement() ?: ""
  }

  fun getMinKey(): String {
    return head.next?.getElement() ?: ""
  }

}

data class Node(val count: Int, val firstString: String) {
  var next: Node? = null
  var prev: Node? = null
  val strs = mutableSetOf<String>(firstString)

  fun detach() {
    prev?.next = next
    next?.prev = prev
    prev = null
    next = null
  }

  fun addAfter(node: Node) {
    val oldNext = next
    next = node
    oldNext?.prev = node
    node.next = oldNext
    node.prev = this
  }

  fun add(str: String) {
    strs.add(str)
  }

  fun remove(str: String) {
    strs.remove(str)
  }

  fun isEmpty(): Boolean {
    return strs.isEmpty()
  }

  fun isValid(): Boolean {
    return count != -1
  }

  fun getElement(): String? {
    for (elem in strs) {
      return elem
    }
    return null
  }
}