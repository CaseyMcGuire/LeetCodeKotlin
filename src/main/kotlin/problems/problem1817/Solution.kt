package problems.problem1817

class Solution {
  fun findingUsersActiveMinutes(logs: Array<IntArray>, k: Int): IntArray {
    val logList = logs.map { Log(it[0], it[1]) }
    val userToUniqueActions = mutableMapOf<Int, MutableSet<Int>>()
    for (log in logList) {
      val actions = userToUniqueActions[log.id] ?: mutableSetOf()
      actions.add(log.minute)
      userToUniqueActions[log.id] = actions
    }

    val uniqueMinutesToUsers = mutableMapOf<Int, Int>()
    for (entry in userToUniqueActions.entries) {
      val userActiveMinutes = entry.value.size
      uniqueMinutesToUsers.merge(userActiveMinutes, 1) { cur, acc -> cur + acc }
    }

    val userActiveMinutes = IntArray(k)
    for (i in 1..k) {
      val activeMinutes = uniqueMinutesToUsers[i]
      userActiveMinutes[i - 1] = activeMinutes ?: 0
    }
    return userActiveMinutes
  }
}

data class Log(val id: Int, val minute: Int)