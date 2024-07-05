package problems.problem1756

class MRUQueue(n: Int) {

  val queue = mutableListOf<Int>()

  init {
    for (i in 1..n) {
      queue.add(i)
    }
  }

  fun fetch(k: Int): Int {
    val element = queue.removeAt(k - 1)
    queue.add(element)
    return element
  }

}