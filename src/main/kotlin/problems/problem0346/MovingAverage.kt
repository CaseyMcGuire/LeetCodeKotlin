package problems.problem0346

import java.util.*

class MovingAverage(var size: Int) {

  private val currentWindow = LinkedList<Int>()

  fun next(`val`: Int): Double {
    if (currentWindow.size == size) {
      currentWindow.removeFirst()
    }
    currentWindow.addLast(`val`)
    val sum = currentWindow.sum()!!
    return sum.toDouble() / currentWindow.size.toDouble()
  }
}