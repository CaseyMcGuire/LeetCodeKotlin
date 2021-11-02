package problems.problem0247

import java.util.*

class Solution {
  fun findStrobogrammatic(n: Int): List<String> {
    val nums = mutableListOf<String>()
    val possibleChoices = listOf('0', '1', '6', '8', '9')
    fun recurse(curString: LinkedList<Char>) {
      if (curString.size == n) {
        if (n > 1 && curString[0] == '0') {
          return
        }
        nums.add(curString.joinToString(""))
        return
      }
      for (choice in possibleChoices) {
        if (choice == '6') {
          curString.addFirst('6')
          curString.addLast('9')
        }
        else if (choice == '9') {
          curString.addFirst('9')
          curString.addLast('6')
        }
        else {
          curString.addFirst(choice)
          curString.addLast(choice)
        }
        recurse(curString)
        curString.removeFirst()
        curString.removeLast()
      }
    }
    if (n % 2 == 0) {
      recurse(LinkedList())
    }
    else {
      for (choice in possibleChoices) {
        if (choice == '6' || choice == '9') {
          continue
        }
        recurse(LinkedList(listOf(choice)))
      }
    }

    return nums
  }
}