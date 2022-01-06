package problems.problem0375

class Solution {
  fun getMoneyAmount(n: Int): Int {
    val cache = mutableMapOf<Item, Int>()
    fun recurse(left: Int, right: Int): Int {
      if (left >= right) {
        return 0
      }
      val item = Item(left, right)
      val value = cache[item]
      if (value != null) {
        return value
      }
      var smallest = Integer.MAX_VALUE
      for (i in left..right) {
        smallest = Math.min(smallest, Math.max(i + recurse(left, i - 1), i + recurse(i + 1, right)))
      }
      cache[item] = smallest
      return smallest
    }
    return recurse(1, n)
  }
}

data class Item(val left: Int, val right: Int)