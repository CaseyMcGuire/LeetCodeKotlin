package problems.problem0771

class Solution {
  fun numJewelsInStones(jewels: String, stones: String): Int {
    val jewelSet = mutableSetOf<Char>()
    for (c in jewels) {
      jewelSet.add(c)
    }

    var numJewelsInStone = 0
    for (c in stones) {
      if (jewelSet.contains(c)) {
        numJewelsInStone++
      }
    }
    return numJewelsInStone
  }
}