package problems.problem0707

class MyLinkedList() {

  private var size = 0
  private var head: ListNode? = null
  private var tail: ListNode? = null

  fun get(index: Int): Int {
    if (index !in 0 until size) {
      return -1
    }

    return getNodeAt(index).value
  }

  private fun getNodeAt(index: Int): ListNode {
    var cur = head!!
    for (i in 0 until index) {
      cur = cur.next!!
    }

    return cur
  }

  fun addAtHead(value: Int) {
    if (initialize(value)) {
      return
    }
    val node = ListNode(value)
    node.next = head
    head = node
    size++
  }

  fun addAtTail(value: Int) {
    if (initialize(value)) {
      return
    }
    val node = ListNode(value)
    tail!!.next = node
    tail = node
    size++
  }

  private fun initialize(value: Int): Boolean {
    if (size != 0) {
      return false
    }

    val node = ListNode(value)
    head = node
    tail = node
    size++
    return true
  }

  fun addAtIndex(index: Int, value: Int) {
    if (index !in 0..size) {
      return
    }

    if (index == 0) {
      addAtHead(value)
    }
    else if (index == size) {
      addAtTail(value)
    }
    else {
      val node = ListNode(value)
      val prev = getNodeAt(index - 1)
      val next = prev.next
      prev.next = node
      node.next = next
      size++
    }
  }

  fun deleteAtIndex(index: Int) {
    if (index !in 0 until size) {
      return
    }

    if (size == 1) {
      head = null
      tail = null
    }
    else if (index == 0) {
      head = head!!.next
    }
    else if (index == size - 1) {
      val prev = getNodeAt(index - 1)
      prev.next = null
      tail = prev
    }
    else {
      val prev = getNodeAt(index - 1)
      prev.next = prev.next?.next
    }
    size--
  }

}

class ListNode(val value: Int) {
  var next: ListNode? = null
}