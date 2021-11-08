package problems.problem0702

 class ArrayReader {
   fun get(index: Int): Int {return 1}
 }

class Solution {
  fun search(reader: ArrayReader, target: Int): Int {
    var start = 0L
    var end = (Integer.MAX_VALUE - 1).toLong()
    while (start <= end) {
      val mid = (start + end) / 2L
      val midNum = reader.get(mid.toInt())
      if (midNum == Integer.MAX_VALUE - 1) {
        end = mid - 1
      }
      else if (midNum == target) {
        return mid.toInt()
      }
      else if (midNum < target) {
        start = mid + 1
      }
      else {
        end = mid - 1
      }
    }
    return -1
  }
}