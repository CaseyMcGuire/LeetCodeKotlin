package problems.problem0692

import java.util.*

class Solution {
  fun topKFrequent(words: Array<String>, k: Int): List<String> {
    val frequencyMap = mutableMapOf<String, Int>()
    for (word in words) {
      frequencyMap.merge(word, 1) { cur, acc -> cur + acc }
    }

    val pq = PriorityQueue<WordFrequency>(compareBy({ -it.frequency }, { it.word }))
    for (entry in frequencyMap.entries) {
      pq.add(WordFrequency(entry.key, entry.value))
    }

    val mostFrequent = mutableListOf<String>()
    for (i in 0 until k) {
      mostFrequent.add(pq.poll().word)
    }
    return mostFrequent
  }
}

data class WordFrequency(val word: String, val frequency: Int)