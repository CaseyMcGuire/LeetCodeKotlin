package problems.problem1155

import java.math.BigInteger
import java.util.*

class Solution {
  fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
    val cache = mutableMapOf<Item, BigInteger>()
    val targetBig = BigInteger(target.toString())
    fun recurse(numDice: Int, curNums: LinkedList<Int>, currentTarget: BigInteger): BigInteger {
      val item = Item(numDice, currentTarget)
      val cached = cache[item]
      if (cached != null) {
        return cached
      }
      if (currentTarget == BigInteger("0") && numDice == n) {
        cache[item] = BigInteger("1")
        return BigInteger("1")
      }
      if (currentTarget < BigInteger("0") || numDice == n) {
        cache[item] = BigInteger("0")
        return BigInteger("0")
      }


      var numWays = BigInteger("0")
      for (i in 1..k) {
        curNums.addLast(i)
        numWays = numWays.add(recurse(numDice + 1, curNums, currentTarget.subtract(BigInteger(i.toString()))))
        curNums.removeLast()
      }
      cache[item] = numWays
      return numWays
    }

    return recurse(0, LinkedList(), BigInteger(target.toString())).mod(BigInteger((Math.pow(10.0, 9.0) + 7).toInt().toString())).toInt()
  }
}

data class Item(val numDice: Int, val target: BigInteger)