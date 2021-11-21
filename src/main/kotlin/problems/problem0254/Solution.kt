package problems.problem0254

class Solution {

  fun getFactors(n: Int): List<List<Int>> {
    val factors = mutableListOf<MutableList<Int>>()
    fun recurse(curList: MutableList<Int>, start: Int, n: Int) {
      if (n == 1) {
        factors.add(curList)
      }
      for (i in start..n) {
        if (n % i == 0) {
          curList.add(i)
          recurse(curList, i, n / i)
          curList.removeAt(i)
        }
      }
    }
    recurse(mutableListOf(), 2, n)
    return factors
  }

}

fun main(args: Array<String>) {
  println(Solution().getFactors(8))
}