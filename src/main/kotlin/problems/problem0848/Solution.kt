package problems.problem0848

class Solution {
  fun shiftingLetters(s: String, shifts: IntArray): String {
    val totalShifts = mutableListOf<Long>()
    var sum = shifts.map { it.toLong() }.sum()
    for (i in shifts.indices) {
      totalShifts.add(sum)
      sum -= shifts[i]
    }


    val shiftedString = StringBuilder()
    for (i in s.indices) {
      val char = s[i]
      val shift = (totalShifts[i] % 26L).toInt()
      val newPosition = ((char - 'a') + shift) % 26
      shiftedString.append(('a' + newPosition))
    }

    return shiftedString.toString()
  }
}