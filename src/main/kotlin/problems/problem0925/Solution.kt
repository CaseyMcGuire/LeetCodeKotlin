package problems.problem0925

class Solution {
  fun isLongPressedName(name: String, typed: String): Boolean {
    var nameIndex = 0
    var typedIndex = 0
    while (nameIndex < name.length && typedIndex < typed.length) {
      if (name[nameIndex] != typed[typedIndex]) {
        return false
      }

      val isAtEnd = nameIndex == name.length - 1 || typedIndex == typed.length - 1
      if (!isAtEnd && name[nameIndex + 1] == typed[typedIndex + 1]) {
        nameIndex++
        typedIndex++
      }
      else {
        var start = typed[typedIndex]
        while (typedIndex < typed.length && typed[typedIndex] == start) {
          typedIndex++
        }
        nameIndex++
      }
    }
    return nameIndex == name.length && typedIndex == typed.length
  }
}