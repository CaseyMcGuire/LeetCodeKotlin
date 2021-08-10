package problems.problem0299

class Solution {
  fun getHint(secret: String, guess: String): String {
    val secretFrequencyMap = mutableMapOf<Char, Int>()
    val guessFrequencyMap = mutableMapOf<Char, Int>()
    var numBulls = 0
    for (i in secret.indices) {
      if (secret[i] == guess[i]) {
        numBulls++
      }
      else {
        secretFrequencyMap.merge(secret[i], 1) { curr, next -> curr + next }
        guessFrequencyMap.merge(guess[i], 1) { curr, next -> curr + next }
      }
    }

    var numCows = 0
    for (entry in guessFrequencyMap.entries) {
      val numInSecret = secretFrequencyMap[entry.key] ?: continue
      numCows += if (entry.value > numInSecret) {
        numInSecret
      } else {
        entry.value
      }
    }
    return "${numBulls}A${numCows}B"
  }
}