package problems.problem0225

import java.util.*

class MyStack() {

  private val queueOne = LinkedList<Int>()
  private val queueTwo = LinkedList<Int>()

  fun push(x: Int) {
    if (queueOne.isEmpty()) {
      queueTwo.addLast(x)
    }
    else {
      queueOne.addLast(x)
    }
  }

  fun pop(): Int {
    val (empty, notEmpty) =
      if (queueOne.isEmpty())
        Pair(queueOne, queueTwo)
      else
        Pair(queueTwo, queueOne)

    while (notEmpty.size != 1) {
      empty.addLast(notEmpty.removeFirst())
    }

    return notEmpty.removeFirst()
  }

  fun top(): Int {
    val last = pop()
    push(last)
    return last
  }

  fun empty(): Boolean {
    return queueOne.isEmpty() && queueTwo.isEmpty()
  }

}