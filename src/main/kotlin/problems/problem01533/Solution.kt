package problems.problem01533

interface ArrayReader {
  // Compares the sum of arr[l..r] with the sum of arr[x..y]
  // return 1 if sum(arr[l..r]) > sum(arr[x..y])
  // return 0 if sum(arr[l..r]) == sum(arr[x..y])
  // return -1 if sum(arr[l..r]) < sum(arr[x..y])
  fun compareSub(l: Int, r: Int, x: Int, y: Int): Int

  // Returns the length of the array
  fun length(): Int
}

class Solution {
  fun getIndex(reader: ArrayReader): Int {
    var start = 0
    var end = reader.length() - 1
    while (true) {

      val mid = (start + end) / 2
      val size = end - start + 1
      if (size == 1) {
        return mid
      }
      val isEven = size % 2 == 0
      val (startEnd, endStart) = if (isEven) Pair(mid, mid + 1) else Pair(mid - 1, mid + 1)
      val comparison = reader.compareSub(start, startEnd, endStart, end)

      if (comparison == 0) {
        return mid
      }
      else if (comparison == 1) {
        if (isEven) end = mid else end = mid - 1
      }
      else {
        start = mid + 1
      }
    }
  }
}