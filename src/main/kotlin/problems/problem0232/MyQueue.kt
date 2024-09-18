package problems.problem0232

import java.util.*

class MyQueue() {

  val stack = LinkedList<Int>()
  val temp = LinkedList<Int>()

  fun push(x: Int) {
    stack.addLast(x)
  }

  fun pop(): Int {
    return getLastElement(true)
  }

  fun peek(): Int {
    return getLastElement(false)
  }

  private fun getLastElement(delete: Boolean): Int {

    while (stack.size > 1) {
      temp.addLast(stack.removeLast())
    }

    val element = stack.removeLast()
    if (!delete) {
      temp.addLast(element)
    }

    while (temp.isNotEmpty()) {
      stack.addLast(temp.removeLast())
    }

    return element
  }

  fun empty(): Boolean {
    return stack.isEmpty()
  }

}