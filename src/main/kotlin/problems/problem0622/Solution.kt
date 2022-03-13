package problems.problem0622

class MyCircularQueue(k: Int) {

  val queue = Array<Int?>(k) { null }
  var frontIndex = 0
  var backIndex = 0
  var size = 0

  fun enQueue(value: Int): Boolean {
    if (isFull()) {
      return false
    }
    queue[backIndex] = value
    size++
    backIndex = getFrontIndex(backIndex)
    return true
  }

  fun deQueue(): Boolean {
    if (isEmpty()) {
      return false
    }
    queue[frontIndex] = null
    size--
    frontIndex = getFrontIndex(frontIndex)
    return true
  }

  fun Front(): Int {
    if (isEmpty()) {
      return -1
    }
    val front = queue[frontIndex]!!
    return front
  }

  fun Rear(): Int {
    if (isEmpty()) {
      return -1
    }
    val prevIndex = getBackIndex(backIndex)
    val last = queue[prevIndex]!!
    return last
  }

  fun isEmpty(): Boolean {
    return size == 0
  }

  fun isFull(): Boolean {
    return size == queue.size
  }

  private fun getFrontIndex(index: Int): Int {
    if (index == queue.size - 1) {
      return 0
    }
    else {
      return index + 1
    }
  }

  private fun getBackIndex(index: Int): Int {
    if (index == 0) {
      return queue.size - 1
    }
    else {
      return index - 1
    }
  }

}