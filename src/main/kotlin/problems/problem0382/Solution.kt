package problems.problem0382

import datastructures.ListNode
import java.util.*

class Solution(head: ListNode?) {

  private val entireList = mutableListOf<Int>()
  private val random = Random()
  init {
    var cur = head
    while (cur != null) {
      entireList.add(cur.`val`)
      cur = cur.next
    }
  }

  fun getRandom(): Int {
    return entireList[random.nextInt(entireList.size)]
  }

}
