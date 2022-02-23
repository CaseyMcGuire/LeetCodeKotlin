package problems.problem1868

class Solution {
  fun findRLEArray(encoded1: Array<IntArray>, encoded2: Array<IntArray>): List<List<Int>> {
    val encoded1List = encoded1.map { Encoding(it[0], it[1]) }
    val encoded2List = encoded2.map { Encoding(it[0], it[1]) }

    var i = 0
    var j = 0

    val productList = mutableListOf<Encoding>()
    while (i < encoded1List.size && j < encoded2List.size) {
      val first = encoded1List[i]
      val second = encoded2List[j]
      val encoding = if (first.frequency < second.frequency) {
        i++
        second.frequency -= first.frequency
        Encoding(first.value * second.value, first.frequency)
      }
      else if (second.frequency < first.frequency) {
        first.frequency -= second.frequency
        j++
        Encoding(first.value * second.value, second.frequency)
      }
      else {
        j++
        i++
        Encoding(first.value * second.value, first.frequency)
      }

      if (productList.isNotEmpty() && productList.last().value == encoding.value) {
        productList.last().frequency += encoding.frequency
      }
      else {
        productList.add(encoding)
      }

    }

    return productList.map { listOf(it.value, it.frequency) }
  }
}

data class Encoding(val value: Int, var frequency: Int)