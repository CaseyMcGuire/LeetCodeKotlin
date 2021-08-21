package problems.problem0900

import java.util.*

class RLEIterator(encoding: IntArray) {

  private val encodings: Deque<Encoding> = LinkedList()

  init {
    for (i in encoding.indices step 2) {
      val numRepeats = encoding[i]
      val num = encoding[i + 1]
      if (numRepeats > 0) {
        encodings.addLast(Encoding(num, numRepeats))
      }
    }
  }

  fun next(n: Int): Int {
    if (encodings.isEmpty()) {
      return -1
    }
    var currentCount = n
    var currentNum = encodings.peekFirst().num
    while (currentCount > 0) {
      if (encodings.isEmpty()) {
        return -1
      }
      var currentEncoding = encodings.peekFirst()
      when {
        currentEncoding.frequency > currentCount -> {
          currentEncoding.frequency -= currentCount
          currentNum = currentEncoding.num
          currentCount = 0
        }
        currentEncoding.frequency <= currentCount -> {
          encodings.removeFirst()
          currentNum = currentEncoding.num
          currentCount -= currentEncoding.frequency
        }
      }
    }
    return currentNum
  }
}

data class Encoding(val num: Int, var frequency: Int)

fun main(args: Array<String>) {
  val iter = RLEIterator(intArrayOf(784,303,477,583,909,505))
  println(iter.next(130))
  println(iter.next(333))
  println(iter.next(238))
  println(iter.next(87))
  println(iter.next(301))
  println(iter.next(276))

}