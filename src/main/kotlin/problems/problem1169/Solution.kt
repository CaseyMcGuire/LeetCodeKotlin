package problems.problem1169

class Solution {
  fun invalidTransactions(stringTransactions: Array<String>): List<String> {
    val possiblyInvalidTransactions = mutableSetOf<Transaction>()
    val transactions = stringTransactions.map { Transaction.fromString(it) }
      .sortedBy { it.time }

    println(transactions)
    val nameToCityToTransaction = mutableMapOf<String, MutableMap<String, MutableSet<Transaction>>>()
    val transactionsInTimeWindow = ArrayDeque<Transaction>()

    for (transaction in transactions) {
      while (transactionsInTimeWindow.isNotEmpty() && transaction.time - transactionsInTimeWindow.first().time > 60) {
        val first = transactionsInTimeWindow.removeFirst()
        val cityToTransaction = nameToCityToTransaction[first.name]!!
        val transactionsInCity = cityToTransaction[first.city]!!
        transactionsInCity.remove(first)

        if (transactionsInCity.isEmpty()) {
          cityToTransaction.remove(first.city)
        }

        if (cityToTransaction.isEmpty()) {
          nameToCityToTransaction.remove(first.name)
        }
      }

      val cityToTransactions = nameToCityToTransaction.getOrPut(transaction.name) { mutableMapOf() }
      var foundPossibleInvalid = false
      for ((city, transactions) in cityToTransactions) {
        if (city == transaction.city) {
          continue
        }
        foundPossibleInvalid = true
        transactions.forEach { possiblyInvalidTransactions.add(it) }
      }

      if (transaction.amount > 1000 || foundPossibleInvalid) {
        possiblyInvalidTransactions.add(transaction)
      }

      cityToTransactions.getOrPut(transaction.city) { mutableSetOf() }.add(transaction)
      transactionsInTimeWindow.add(transaction)
    }

    return possiblyInvalidTransactions.map { it.str }
  }

  class Transaction(
    val name: String,
    val time: Int,
    val amount: Int,
    val city: String,
    val str: String
  ) {
    companion object {
      fun fromString(str: String): Transaction {
        val components = str.split(",")
        return Transaction(
          components[0],
          components[1].toInt(),
          components[2].toInt(),
          components[3],
          str
        )
      }
    }
  }
}