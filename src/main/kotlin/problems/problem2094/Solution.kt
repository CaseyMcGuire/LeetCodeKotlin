package problems.problem2094

import java.util.LinkedList

class Solution {
  fun findEvenNumbers(digits: IntArray): IntArray {
    val frequencyMap = mutableMapOf<Int, Int>()
    for (digit in digits) {
      frequencyMap.merge(digit, 1) { cur, acc -> Math.min(cur + acc, 3) }
    }

    val flattenedDigits = frequencyMap.flattenToList()

    val evenNumbers = mutableSetOf<Int>()
    val curNum = LinkedList<Int>()
    for (i in digits.indices) {
      if (digits[i] == digits.getOrNull(i - 1) || digits[i] % 2 != 0) {
        continue
      }
      curNum.addFirst(digits[i])
      digits.swap(i, digits.size - 1)
      for (j in 0 until digits.size - 1) {
        curNum.addFirst(digits[j])
        digits.swap(j, digits.size - 2)
        for (k in 0 until digits.size - 2) {
          curNum.addFirst(digits[k])
          val evenNum = curNum.joinToString("").toInt()
          if (evenNum >= 100) {
            evenNumbers.add(evenNum)
          }
          curNum.removeFirst()
        }
        digits.swap(j, digits.size - 2)
        curNum.removeFirst()
      }
      curNum.removeFirst()
      digits.swap(i, digits.size - 1)
    }

    return evenNumbers.toList().sorted().toIntArray()
  }

  fun IntArray.swap(first: Int, second: Int) {
    val temp = this[first]
    this[first] = this[second]
    this[second] = temp
  }

  fun MutableMap<Int, Int>.flattenToList(): List<Int> {
    val nums = mutableListOf<Int>()
    for (entry in this.entries) {
      for (i in 0 until entry.value) {
        nums.add(entry.key)
      }
    }
    return nums.sorted()
  }
}