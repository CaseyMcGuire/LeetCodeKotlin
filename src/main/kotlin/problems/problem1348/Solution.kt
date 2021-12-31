package problems.problem1348

import java.util.*

class TweetCounts() {

  private val tweetToTimes = mutableMapOf<String, TreeMap<Int, Int>>()

  fun recordTweet(tweetName: String, time: Int) {
    val times = tweetToTimes[tweetName] ?: TreeMap()
    times.increment(time)
    tweetToTimes[tweetName] = times
  }

  fun getTweetCountsPerFrequency(freq: String, tweetName: String, startTime: Int, endTime: Int): List<Int> {
    val times = tweetToTimes[tweetName] ?: return emptyList()
    val incrementAmount = getIncrementAmount(freq)
    var chunkStart = (startTime / incrementAmount) * incrementAmount
    if (startTime > chunkStart) {
      chunkStart = startTime
    }
    var currentTime = chunkStart + incrementAmount
    var isLastChunk = false
    if (currentTime > endTime) {
      isLastChunk = true
      currentTime = endTime
    }
    var prevTime = chunkStart
    val frequencies = mutableListOf<Int>()
    while (true) {
      val tweetsInTime = times.subMap(prevTime, true, currentTime, isLastChunk)
      val total = tweetsInTime.values.sum()
      frequencies.add(total)
      if (isLastChunk) {
        break
      }
      prevTime = currentTime
      currentTime = currentTime + incrementAmount
      if (currentTime > endTime) {
        isLastChunk = true
        currentTime = endTime
      }
    }
    return frequencies
  }

  private fun getIncrementAmount(freq: String): Int {
    return when(freq) {
      "minute" -> 60
      "hour" -> 3600
      "day" -> 86400
      else -> throw RuntimeException()
    }
  }


  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val currentFrequency = this[num] ?: return
    if (currentFrequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = currentFrequency - 1
    }
  }

}