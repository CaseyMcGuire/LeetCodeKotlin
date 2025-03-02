package problems.problem0170

class TwoSum() {

  val frequencyMap = sortedMapOf<Int, Int>()

  fun add(number: Int) {
    frequencyMap.merge(number, 1) { acc, cur -> 2 }
  }

  fun find(value: Int): Boolean {
    for (entry in frequencyMap.entries) {
      if (entry.key * 2 > value) {
        return false
      }
      val remainder = value - entry.key
      if (remainder == entry.key) {
        if (entry.value == 2) {
          return true
        }
      }
      else if (frequencyMap.containsKey(remainder)) {
        return true
      }
    }
    return false
  }

}
