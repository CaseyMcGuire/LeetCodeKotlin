package problems.problem1114

internal class Foo {
  private var turn = 0
  @Throws(InterruptedException::class)
  fun first(printFirst: Runnable) {
    printFirst.run()
    turn++
  }

  @Throws(InterruptedException::class)
  fun second(printSecond: Runnable) {
    while (turn != 1) {
    }
    printSecond.run()
    turn++
  }

  @Throws(InterruptedException::class)
  fun third(printThird: Runnable) {
    while (turn != 2) {
    }
    printThird.run()
    turn++
  }
}