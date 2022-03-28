package problems.problem1130

class Solution {
  fun mctFromLeafValues(arr: IntArray): Int {
    if (arr.size in 0..1) {
      return 0
    }

    val cache = mutableMapOf<CacheKey, Result>()

    fun evaluate(start: Int, end: Int): Result {
      val key = CacheKey(start, end)
      val cachedValue = cache[key]
      if (cachedValue != null) {
        return cachedValue
      }
      val difference = end - start
      if (difference == 0) {
        return Result(0, arr[start])
      }
      if (difference == 1) {
        return Result(arr[start] * arr[end], Math.max(arr[start], arr[end]))
      }
      var minSoFar: Result? = null
      for (i in start until end) {
        val leftResult = evaluate(start, i)
        val rightResult = evaluate(i + 1, end)

        val result = leftResult.largestLeafValue * rightResult.largestLeafValue
        if (minSoFar == null || minSoFar.sum > leftResult.sum + rightResult.sum + result) {
          minSoFar = Result(leftResult.sum + rightResult.sum + result, Math.max(leftResult.largestLeafValue, rightResult.largestLeafValue))
        }
      }
      cache[key] = minSoFar!!
      return minSoFar!!
    }

    return evaluate(0, arr.size - 1).sum
  }
}

data class Result(val sum: Int, val largestLeafValue: Int)
data class CacheKey(val start: Int, val end: Int)