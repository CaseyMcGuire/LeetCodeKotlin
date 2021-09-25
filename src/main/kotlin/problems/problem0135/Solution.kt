package problems.problem0135

import java.util.*

class Solution {
  fun candy(ratings: IntArray): Int {
    val pq = PriorityQueue<Rating>(compareBy { it.value })
    val candies = IntArray(ratings.size)
    for (index in ratings.indices) {
      pq.add(Rating(ratings[index], index))
    }
    while (pq.isNotEmpty()) {
      val current = pq.poll()
      val left = if (current.index > 0) Rating(ratings[current.index - 1], current.index - 1) else null
      val right = if (current.index < ratings.size - 1) Rating(ratings[current.index + 1], current.index + 1) else null
      val smaller = arrayListOf(left, right)
        .filterNotNull()
        .filter {it.value < current.value}
        .maxBy { candies[it.index] }

      when {
        smaller == null -> {
          candies[current.index] = 1
        }
        smaller.value == current.value -> {
          candies[current.index] = candies[smaller.index]
        }
        else -> {
          candies[current.index] = candies[smaller.index] + 1
        }
      }

    }
    return candies.sum()
  }
}

data class Rating(val value: Int, val index: Int)

fun main(args: Array<String>) {
  println(Solution().candy(intArrayOf(1,2,2)))
}