package problems.problem1244

import java.util.*
import kotlin.collections.HashMap

class Leaderboard() {
  private val scoreToPlayers = TreeMap<Int, MutableSet<Int>>()
  private val playerToScore = HashMap<Int, Int>()

  fun addScore(playerId: Int, score: Int) {
    val currentScore = playerToScore[playerId]
    if (currentScore == null) {
      val currentPlayers = scoreToPlayers[score] ?: mutableSetOf()
      currentPlayers.add(playerId)
      scoreToPlayers[score] = currentPlayers
      playerToScore[playerId] = score
    }
    else {
      val newScore = score + currentScore
      val oldScoreSet = scoreToPlayers[currentScore]!!
      oldScoreSet.remove(playerId)

      val newScoreSet = scoreToPlayers[newScore] ?: mutableSetOf()
      newScoreSet.add(playerId)

      scoreToPlayers[newScore] = newScoreSet
      playerToScore[playerId] = newScore
    }
  }

  fun top(K: Int): Int {
    val pq = PriorityQueue<Int>(compareByDescending { it })
    var sum = 0
    for (entry in scoreToPlayers.entries) {
      val size = entry.value.size
      for (i in 0 until size) {
        pq.add(entry.key)
      }
    }
    var i = 0
    while (i < K) {
      if (pq.isEmpty()) {
        break
      }
      sum += pq.poll()
      i++
    }
    return sum
  }

  fun reset(playerId: Int) {
    val score = playerToScore[playerId]!!
    val set = scoreToPlayers[score]!!
    set.remove(playerId)
    playerToScore.remove(playerId)
  }
}