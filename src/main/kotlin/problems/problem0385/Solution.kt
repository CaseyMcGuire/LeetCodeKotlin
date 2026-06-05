package problems.problem0385

import problems.problem0341.NestedInteger

class Solution {
  fun deserialize(s: String): NestedInteger {
    var i = 0

    fun nextInt(): Int {
      val builder = StringBuilder()
      while (i < s.length && (s[i].isDigit() || s[i] == '-')) {
        builder.append(s[i])
        i++
      }

      if (s.getOrNull(i) == ',') {
        i++
      }

      return builder.toString().toInt()
    }

    fun recurse(): NestedInteger {
      if (s[i] == '[') {
        val nested = NestedInteger()
        i++
        while (s[i] != ']') {
          if (s[i] == '[') {
            nested.add(recurse())
          }
          else {
            nested.add(NestedInteger(nextInt()))
          }
        }
        i++
        if (s.getOrNull(i) == ',') {
          i++
        }
        return nested
      }
      else {
        return NestedInteger(nextInt())
      }
    }

    return recurse()
  }
}