package problems.problem0914

class Solution {
  fun hasGroupsSizeX(deck: IntArray): Boolean {
    val frequencyMap = mutableMapOf<Int, Int>()
    for (card in deck) {
      frequencyMap.merge(card, 1) { cur, acc -> cur + acc }
    }
    val values = frequencyMap.values.toList()
    val divisors = values.map { getDivisors(it) }
    var commonDivisors = divisors[0].toSet()
    for (divisor in divisors) {
      commonDivisors = commonDivisors intersect divisor
    }
    val copy = commonDivisors.toMutableSet()
    copy.remove(1)
    return copy.isNotEmpty()
  }

  private fun getDivisors(num: Int): List<Int> {
    val divisors = mutableListOf<Int>()
    for (i in 1..Math.sqrt(num.toDouble()).toInt()) {
      val isDivisible = num % i == 0
      if (!isDivisible) {
        continue
      }
      val factor = num / i
      divisors.add(i)
      divisors.add(factor)
    }
    return divisors
  }
}