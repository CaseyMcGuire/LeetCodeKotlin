package problems.problem1054

import java.util.PriorityQueue

class Solution {
  fun rearrangeBarcodes(barcodes: IntArray): IntArray {
    val pq = PriorityQueue<IntFrequency>(compareByDescending { it.frequency })
    val frequencyMap = mutableMapOf<Int, Int>()

    for (barcode in barcodes) {
      frequencyMap.merge(barcode, 1) { cur, acc -> cur + acc }
    }

    for (entry in frequencyMap) {
      pq.add(IntFrequency(entry.key, entry.value))
    }

    val rearranged = mutableListOf<Int>()
    while (pq.isNotEmpty()) {
      val largest = pq.poll()
      if (largest.num == rearranged.lastOrNull()) {
        val secondLargest = pq.poll()
        rearranged.add(secondLargest.num)
        secondLargest.frequency--

        if (secondLargest.frequency > 0) {
          pq.add(secondLargest)
        }
      }
      else {
        rearranged.add(largest.num)
        largest.frequency--
      }

      if (largest.frequency > 0) {
        pq.add(largest)
      }
    }

    return rearranged.toIntArray()
  }

  data class IntFrequency(val num: Int, var frequency: Int)
}