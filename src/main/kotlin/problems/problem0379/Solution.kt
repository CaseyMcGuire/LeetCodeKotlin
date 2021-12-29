package problems.problem0379

import java.util.*

class PhoneDirectory(maxNumbers: Int) {

  private val availableNumbers = TreeSet<Int>()

  init {
    for (i in 0 until maxNumbers) {
      availableNumbers.add(i)
    }
  }

  fun get(): Int {
    if (availableNumbers.isEmpty()) {
      return -1
    }
    val available = availableNumbers.first()
    availableNumbers.remove(available)
    return available
  }

  fun check(number: Int): Boolean {
    return availableNumbers.contains(number)
  }

  fun release(number: Int) {
    availableNumbers.add(number)
  }

}