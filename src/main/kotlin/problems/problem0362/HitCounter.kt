package problems.problem0362

import java.util.*

class HitCounter() {

  private val timestampToCount: TreeMap<Int, Int>
  init {
    timestampToCount = TreeMap<Int, Int>()
  }

  fun hit(timestamp: Int) {
    timestampToCount.merge(timestamp, 1) { new, current -> new + current }
  }

  fun getHits(timestamp: Int): Int {
    val hitsInLast5Minutes = timestampToCount.subMap(timestamp - 300, false, timestamp, true)
    return hitsInLast5Minutes.values.sum() ?: 0
  }

}
