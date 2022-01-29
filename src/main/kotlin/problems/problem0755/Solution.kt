package problems.problem0755

import java.util.*

class Solution {
  fun pourWater(heights: IntArray, volume: Int, k: Int): IntArray {
    val leftIndices = PriorityQueue<Int>(compareBy({heights[it]}, {-it}))
    val rightIndices = PriorityQueue<Int>(compareBy({heights[it]}, {it}))

    var leftIndex = k - 1
    var rightIndex = k + 1
    fun findMaxIndexAndElements(left: Boolean) {
      var curHeight = heights[k]
      var index = if (left) leftIndex else rightIndex
      val pq = if (left) leftIndices else rightIndices
      while (index in 0 until heights.size && heights[index] <= curHeight) {
        if (pq.isNotEmpty() && heights[pq.peek()] < heights[index]) {
          break
        }
        pq.add(index)
        if (left) {
          index--
        }
        else {
          index++
        }
      }
      if (left) {
        leftIndex = index
      }
      else {
        rightIndex = index
      }
    }

    findMaxIndexAndElements(true)
    findMaxIndexAndElements(false)

    for (i in 0 until volume) {
      val curHeight = heights[k]
      if (leftIndices.isNotEmpty() && heights[leftIndices.peek()] < curHeight)  {
        val index = leftIndices.poll()
        heights[index]++
        leftIndices.add(index)
      }
      else if (rightIndices.isNotEmpty() && heights[rightIndices.peek()] < curHeight) {
        val index = rightIndices.poll()
        heights[index]++
        rightIndices.add(index)
      }
      else {
        heights[k]++
      }

      findMaxIndexAndElements(true)
      findMaxIndexAndElements(false)
    }

    return heights

  }

}