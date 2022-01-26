package problems.problem1128

class Solution {
  fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
    val dominoesToNum = mutableMapOf<Domino, Int>()
    val dominoesList = dominoes.map { Domino(it[0], it[1]) }

    for (domino in dominoes) {
      val i = domino[0]
      val j = domino[1]
      val smaller = if (i < j) Domino(i, j) else Domino(j, i)
      dominoesToNum.merge(smaller, 1) { cur, acc -> cur + acc }
    }

    return dominoesToNum.values.map { ((it - 1) * (it)) / 2 }.sum()
  }
}

data class Domino(val i: Int, val j: Int) {
  fun reversed(): Domino = Domino(j, i)
}