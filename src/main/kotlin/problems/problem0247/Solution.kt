package problems.problem0247

import java.util.*

class Solution {
  fun findStrobogrammatic(n: Int): List<String> {
    val flippableNums = mutableSetOf<Char>('0', '1', '6', '8', '9')
    val strobogrammaticNumbers = mutableListOf<String>()

    val curNum = MutableList<Char>(n) { '0' }
    fun recurse(i: Int, j: Int) {
      if (i > j) {
        strobogrammaticNumbers.add(curNum.joinToString(""))
        return
      }
      else if (i == j) {
        for (num in flippableNums) {
          if (num == getRotatedNum(num)) {
            curNum[i] = num
            strobogrammaticNumbers.add(curNum.joinToString(""))
          }
        }
        return
      }

      for (num in flippableNums) {
        if (i == 0 && num == '0') {
          continue
        }
        curNum[i] = num
        curNum[j] = getRotatedNum(num)
        recurse(i + 1, j - 1)
      }
    }
    recurse(0, n - 1)
    return strobogrammaticNumbers
  }

  private fun getRotatedNum(num: Char): Char {
    return when (num) {
      '0' -> '0'
      '1' -> '1'
      '6' -> '9'
      '8' -> '8'
      '9' -> '6'
      else -> throw IllegalArgumentException("Can't rotate $num")
    }
  }
}