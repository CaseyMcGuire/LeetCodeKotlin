package problems.problem0716

import java.util.*

class MaxStack() {

  private val pq = PriorityQueue<Node>(compareByDescending<Node>({it.num}).thenByDescending({it.order}))
  private val stack = ArrayDeque<Node>()
  private var order = 0

  fun push(x: Int) {
    val node = Node(x, order)
    order++
    pq.add(node)
    stack.push(node)
  }

  fun pop(): Int {
    val node = stack.popNonDeleted()
    val num = node.num
    node.isDeleted = true
    return num
  }

  fun top(): Int {
    val node = stack.popNonDeleted()
    val num = node.num
    stack.push(node)
    return num
  }

  fun peekMax(): Int {
    val max = pq.popNonDeleted()
    val num = max.num
    pq.add(max)
    return num
  }

  fun popMax(): Int {
    val max = pq.popNonDeleted()
    max.isDeleted = true
    return max.num
  }

  private fun PriorityQueue<Node>.popNonDeleted(): Node {
    while (this.isNotEmpty()) {
      val cur = this.poll()
      if (cur.isDeleted) {
        continue
      }
      return cur
    }
    throw RuntimeException()
  }

  private fun ArrayDeque<Node>.popNonDeleted(): Node {
    while (this.isNotEmpty()) {
      var cur = this.pop()
      if (cur.isDeleted) {
        continue
      }
      return cur
    }
    throw RuntimeException()
  }

}

data class Node(val num: Int, val order: Int, var isDeleted: Boolean = false)