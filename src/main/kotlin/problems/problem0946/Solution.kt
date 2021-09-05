package problems.problem0946

import java.util.*

class Solution {
  fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {
    if (pushed.isEmpty()) {
      return true
    }
    val stack = ArrayDeque<Int>()
    val indexOfFirstPop = pushed.indexOf(popped[0])
    for (i in 0..indexOfFirstPop) {
      stack.push(pushed[i])
    }
    var popIndex = 0
    var pushedIndex = indexOfFirstPop + 1
    // if popped[popIndex] is at the head of the stack, it has to be ahead of pushed list
    while (popIndex < popped.size) {
      if (popped[popIndex] == stack.peek()) {
        stack.pop()
        popIndex++
      }
      else if (pushedIndex < pushed.size)  {
        // popped is not at the head, let's try to find it ahead
        while (pushedIndex < pushed.size) {
          val foundValue = pushed[pushedIndex] == popped[popIndex]
          stack.push(pushed[pushedIndex])
          pushedIndex++
          if (foundValue) {
            break
          }
        }
      }
      else {
        return false
      }
    }
    return true

  }
}

fun main(args: Array<String>) {
  println(Solution().validateStackSequences(intArrayOf(1,2,3,4,5), intArrayOf(4,5,3,2,1)))
}