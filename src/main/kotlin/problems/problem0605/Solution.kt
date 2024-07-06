package problems.problem0605

class Solution {
  fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    var i = 0
    var numPlacements = 0
    while (i < flowerbed.size) {
      if (flowerbed[i] == 0 &&
        (flowerbed.getOrN605ull(i - 1) ?: 0) == 0 &&
        (flowerbed.getOrNull(i + 1) ?: 0) == 0) {
        numPlacements++
        i += 2
      }
      else {
        i++
      }
    }
    return numPlacements >= n
  }
}