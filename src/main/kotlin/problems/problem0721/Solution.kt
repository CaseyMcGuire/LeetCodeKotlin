package problems.problem0721

class Solution {
  fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
    val accountList = accounts.map { Account(it[0], it.subList(1, it.size).toMutableSet()) }.toMutableList()
    val indexToAccount = mutableMapOf<Int, Account>()
    val emailToIndex = mutableMapOf<String, Int>()

    for (i in accountList.indices) {
      indexToAccount[i] = accountList[i]
    }
    for (i in accountList.indices) {
      val account = accountList[i]
      var existingIndices = mutableSetOf<Int>(i)
      for (email in account.emails) {
        val index = emailToIndex[email]
        if (index != null) {
          existingIndices.add(index)
        }
      }
      val existingList = existingIndices.toList()
      val first = existingList[0]
      val firstAccount = indexToAccount[first]!!
      for (i in 1 until existingList.size) {
        val otherId = existingList[i]
        val otherAccount = indexToAccount[existingList[i]]!!
        firstAccount.emails.addAll(otherAccount.emails)
        indexToAccount.remove(otherId)
      }
      for (email in firstAccount.emails) {
        emailToIndex[email] = first
      }
    }

    return indexToAccount.values.map { listOf(it.name) + it.emails.sorted() }
  }
}

data class Account(val name: String, val emails: MutableSet<String>)