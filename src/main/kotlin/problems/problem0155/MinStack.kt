package problems.problem0155

import java.util.*

class MinStack {

  private val stack = ArrayDeque<Node>()

  fun push(`val`: Int) {
    if (stack.isEmpty()) {
      stack.push(Node(`val`, `val`.toLong()))
      return
    }
    val minValue = getMin()
    if (`val` < minValue) {
      stack.push(Node(`val`, `val`.toLong()))
    }
    else {
      stack.push(Node(`val`, minValue.toLong()))
    }
  }

  fun pop() {
    stack.pop()
  }

  fun top(): Int {
    return stack.peek().value
  }

  fun getMin(): Int {
    return stack.peek().minValue.toInt()
  }
}

data class Node(val value: Int, val minValue: Long)

fun main(args: Array<String>) {
  val stack = MinStack()
  stack.push(2147483646)
  stack.push(2147483646)
  stack.push(2147483647)
  stack.top()
  stack.pop()
  stack.getMin()
  stack.pop()
  stack.getMin()
  stack.pop()
  stack.push(2147483647)
  stack.top()
  stack.getMin()
  stack.push(-2147483648)
  stack.top()
  stack.getMin()
  stack.pop()
  stack.getMin()
}