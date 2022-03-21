package problems.problem0465

class Solution {
  // O(N^N)
  fun minTransfers(transactions: Array<IntArray>): Int {
    val personToBalance = mutableMapOf<Int, Int>()
    for (transaction in transactions) {
      val firstPerson = transaction[0]
      val secondPerson = transaction[1]
      val amount = transaction[2]
      personToBalance.merge(firstPerson, -amount) { acc, cur -> acc + cur }
      personToBalance.merge(secondPerson, amount) { acc, cur -> acc + cur }
    }

    var minTransactions: Int? = null
    val peopleWithPositiveBalances = personToBalance.entries.filter { it.value > 0 }.map { it.key }.toMutableList()
    val peopleWithNegativeBalances = personToBalance.entries.filter { it.value < 0 }.map { it.key }

    fun dfs(transactions: Int) {
      if (minTransactions != null && transactions >= minTransactions!!) {
        return
      }
      if (peopleWithPositiveBalances.isEmpty()) {
        if (minTransactions == null || transactions < minTransactions!!) {
          minTransactions = transactions
        }
        return
      }

      val person = peopleWithPositiveBalances.last()!!

      val curBalance = personToBalance[person]!!

      for (otherPerson in peopleWithNegativeBalances) {
        val otherBalance = personToBalance[otherPerson]!!
        if (otherBalance == 0) {
          continue
        }
        if (Math.abs(otherBalance) < curBalance) {
          val newCurBalance = curBalance + otherBalance
          personToBalance[otherPerson] = 0
          personToBalance[person] = newCurBalance
          dfs(transactions + 1)
          personToBalance[otherPerson] = otherBalance
          personToBalance[person] = curBalance
        }
        else {
          peopleWithPositiveBalances.removeAt(peopleWithPositiveBalances.size - 1)
          val newOtherBalance = curBalance + otherBalance
          personToBalance[person] = 0
          personToBalance[otherPerson] = newOtherBalance
          dfs(transactions + 1)
          personToBalance[person] = curBalance
          personToBalance[otherPerson] = otherBalance
          peopleWithPositiveBalances.add(person)
        }
      }

    }

    dfs(0)

    return minTransactions!!
  }
}