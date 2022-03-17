package problems.problem1058

import java.math.BigDecimal
import java.util.*

class Solution {
  fun minimizeError(prices: Array<String>, target: Int): String {
    var min = 0
    var max = 0
    for (price in prices) {
      val floor = Math.floor(price.toDouble()).toInt()
      val ceiling = Math.ceil(price.toDouble()).toInt()
      min += floor
      max += ceiling
    }
    if (target !in min..max) {
      return "-1"
    }

    var remainder = target - min

    val pq = PriorityQueue<Price>(compareBy({ it.getCeilDifference() }))
    for (price in prices) {
      pq.add(Price(price))
    }

    // we need find the number of decimals to iterate by 1
    var total = 0.toBigDecimal()
    while (remainder != 0) {
      val cur = pq.poll()

      // if floor and ceiling are the same, it means we can't iterate by 1
      if (cur.getCeiling() != cur.getFloor()) {
        total += cur.getCeilDifference()
        remainder--
      }
    }

    while (pq.isNotEmpty()) {
      total += pq.poll().getFloorDifference()
    }

    return String.format("%.3f", total.toDouble());
  }
}

data class Price(val price: String) {

  fun getCeiling(): BigDecimal {
    return Math.ceil(price.toDouble()).toBigDecimal()
  }

  fun getFloor(): BigDecimal {
    return Math.floor(price.toDouble()).toBigDecimal()
  }

  fun getCeilDifference(): BigDecimal {
    return (Math.ceil(price.toDouble()).toBigDecimal() - price.toDouble().toBigDecimal()).abs()
  }

  fun getFloorDifference(): BigDecimal {
    return (price.toDouble().toBigDecimal() - Math.floor(price.toDouble()).toBigDecimal()).abs()
  }
}