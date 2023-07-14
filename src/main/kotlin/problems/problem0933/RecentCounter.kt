package problems.problem0933

import java.util.*

class RecentCounter() {

  val pings = LinkedList<Int>()

  fun ping(t: Int): Int {
    pings.addLast(t)
    while (pings.peekFirst() < t - 3000) {
      pings.removeFirst()
    }
    return pings.size
  }
}