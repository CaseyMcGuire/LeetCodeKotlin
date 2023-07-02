package problems.problem0374

class Solution: GuessGame() {
  override fun guessNumber(n: Int):Int {
    var start = 1
    var end = n
    while (true) {
      val mid = ((start.toLong() + end.toLong()) / 2L).toInt()
      val guessResult = guess(mid)
      if (guessResult == 0) {
        return mid
      }
      else if (guessResult == 1) {
        start = mid + 1
      }
      else {
        end = mid - 1
      }
    }

  }
}

abstract class GuessGame() {
  abstract fun guessNumber(n: Int): Int
  fun guess(n: Int): Int {
    return 0
  }
}