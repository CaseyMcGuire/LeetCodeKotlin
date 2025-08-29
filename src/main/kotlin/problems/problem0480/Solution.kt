package problems.problem0480

import java.util.*

class Solution {
  fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
    val medians = mutableListOf<Double>()
    val slidingWindow = SlidingWindow()
    for (i in nums.indices) {
      val element = Element(nums[i], i)
      slidingWindow.add(element)
      if (slidingWindow.size() > k) {
        val elementToRemove = Element(nums[i - k], i - k)
        slidingWindow.remove(elementToRemove)
      }

      if (slidingWindow.size() == k) {
        medians.add(slidingWindow.getMedian())
      }
    }
    return medians.toDoubleArray()
  }
}

class SlidingWindow {
  private val smallerHalf = TreeSet<Element>(compareBy({ it.value }, { it.index }))
  private val largerHalf = TreeSet<Element>(compareBy({ it.value }, { it.index }))

  fun add(element: Element) {
    largerHalf.add(element)
    rebalance()
  }

  fun remove(element: Element) {
    smallerHalf.remove(element)
    largerHalf.remove(element)
    rebalance()
  }

  private fun rebalance() {
    while (smallerHalf.size < largerHalf.size) {
      smallerHalf.add(largerHalf.pollFirst())
    }

    while (smallerHalf.size - largerHalf.size > 1) {
      largerHalf.add(smallerHalf.pollLast())
    }

    if (largerHalf.isNotEmpty() && smallerHalf.last().value > largerHalf.first().value) {
      val first = largerHalf.pollFirst()
      val last = smallerHalf.pollLast()
      largerHalf.add(last)
      smallerHalf.add(first)
    }
  }

  fun size(): Int {
    return smallerHalf.size + largerHalf.size
  }

  fun getMedian(): Double {
    val size = smallerHalf.size + largerHalf.size
    if (size % 2 == 1) {
      return smallerHalf.last().value.toDouble()
    }
    else {
      return (smallerHalf.last().value.toDouble() + largerHalf.first().value.toDouble()) / 2.0
    }
  }
}

data class Element(val value: Int, val index: Int)