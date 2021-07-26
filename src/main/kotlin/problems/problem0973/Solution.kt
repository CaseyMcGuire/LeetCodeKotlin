package problems.problem0973

import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class Solution {
  fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    val pq = PriorityQueue<IntArray> { o1, o2 ->
      val first = sqrt((o1[0]).toDouble().pow(2) + (o1[1]).toDouble().pow(2))
      val second = sqrt((o2[0]).toDouble().pow(2) + (o2[1]).toDouble().pow(2))
      when {
        first > second -> {
          1
        }
        first == second -> {
          0
        }
        else -> {
          -1
        }
      }
    }
    for (point in points) {
      pq.add(point)
    }
    val list = mutableListOf<IntArray>()
    for (i in 0 until k) {
      list.add(pq.poll())
    }
    return list.toTypedArray()
  }
}

fun main(args: Array<String>) {
  println(Solution().kClosest(
    arrayOf(intArrayOf(3,3), intArrayOf(5,-1), intArrayOf(-2, 4)),
    2
  ))
}