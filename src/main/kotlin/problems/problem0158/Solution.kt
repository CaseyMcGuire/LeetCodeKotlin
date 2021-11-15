package problems.problem0158

import problems.problem0157.Reader4
import java.util.*

class Solution : Reader4() {

  private val queue = LinkedList<Char>()

  override fun read(buf:CharArray, n:Int): Int {
    while (queue.size < n) {
      val buffer = CharArray(4)
      val length = read4(buffer)
      if (length == 0) {
        break
      }

      for (j in 0 until length) {
        queue.addLast(buffer[j])
      }
      if (length < 4) {
        break
      }
    }

    var i = 0
    while (queue.isNotEmpty() && i < n) {
      buf[i] = queue.removeFirst()
      i++
    }
    return i
  }
}