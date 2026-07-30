package problems.problem0641

class MyCircularDeque(private val k: Int) {

  private val head = Node(-1)
  private val tail = Node(-1)
  private var size = 0

  fun insertFront(value: Int): Boolean {
    val node = Node(value)
    if (size == k) {
      return false
    }
    size++
    if (size == 1) {
      head.next = node
      tail.prev = node
      return true
    }
    node.next = head.next
    head.next!!.prev = node
    head.next = node
    return true
  }

  fun insertLast(value: Int): Boolean {
    val node = Node(value)
    if (size == k) {
      return false
    }
    size++
    if (size == 1) {
      head.next = node
      tail.prev = node
      return true
    }
    node.prev = tail.prev
    tail.prev!!.next = node
    tail.prev = node
    return true
  }

  fun deleteFront(): Boolean {
    if (size == 0) {
      return false
    }
    size--

    if (size == 0) {
      head.next = null
      tail.prev = null
      return true
    }

    head.next = head.next!!.next
    head.next!!.prev = null
    return true
  }

  fun deleteLast(): Boolean {
    if (size == 0) {
      return false
    }
    size--

    if (size == 0) {
      head.next = null
      tail.prev = null
      return true
    }

    tail.prev = tail.prev!!.prev
    tail.prev!!.next = null
    return true
  }

  fun getFront(): Int {
    return head.next?.value ?: -1
  }

  fun getRear(): Int {
    return tail.prev?.value ?: -1
  }

  fun isEmpty(): Boolean {
    return size == 0
  }

  fun isFull(): Boolean {
    return size == k
  }
}

class Node(val value: Int) {
  var next: Node? = null
  var prev: Node? = null
}
