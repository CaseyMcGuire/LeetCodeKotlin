package problems.problem0277

class Solution: Relation() {
  override fun findCelebrity(n: Int) : Int {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 0 until n) {
      graph[i] = mutableSetOf<Int>()
    }
    for (i in 0 until n) {
      for (j in 0 until n) {
        if (j == i) {
          continue
        }
        if (knows(i, j)) {
          graph[i]!!.add(j)
        }
      }
    }

    val possibleCelebrities = graph.entries.filter { it.value.isEmpty() }
    if (possibleCelebrities.size != 1) {
      return -1
    }
    val celebrity = possibleCelebrities[0].key
    for (i in 0 until n) {
      if (celebrity == i) {
        continue
      }
      if (!graph[i]!!.contains(celebrity)) {
        return -1
      }
    }
    return celebrity
  }
}

abstract class Relation {

  abstract fun findCelebrity(n: Int): Int


  fun knows(a: Int, b: Int): Boolean {
    return true
  }
}
