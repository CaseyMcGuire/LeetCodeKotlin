package problems.problem1564

import java.util.*

class Solution {
  fun maxBoxesInWarehouse(boxes: IntArray, warehouse: IntArray): Int {
    if (boxes.isEmpty() || warehouse.isEmpty()) {
      return 0
    }
    val maxHeights = IntArray(warehouse.size)
    var maxHeight = warehouse[0]
    for (i in maxHeights.indices) {
      val curHeight = warehouse[i]
      if (curHeight < maxHeight) {
        maxHeight = curHeight
      }
      maxHeights[i] = maxHeight
    }
    val pq = PriorityQueue<Int>(compareBy { it })
    for (box in boxes) {
      pq.add(box)
    }
    var boxesSoFar = 0
    for (i in maxHeights.indices.reversed()) {
      if (pq.isEmpty()) {
        break
      }
      val maxHeight = maxHeights[i]
      val smallest = pq.peek()

      if (maxHeight >= smallest) {
        boxesSoFar++
        pq.poll()
      }
    }
    return boxesSoFar
  }

}