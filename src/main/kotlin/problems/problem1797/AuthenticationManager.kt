package problems.problem1797

import java.util.*

class AuthenticationManager(val timeToLive: Int) {

  private val tokenIdToExpirationTime = mutableMapOf<String, Int>()
  private val expirationTimeToNumTokens = TreeMap<Int, Int>()

  fun generate(tokenId: String, currentTime: Int) {
    val expirationTime = timeToLive + currentTime
    tokenIdToExpirationTime[tokenId] = expirationTime
    val numTokensAtTime = expirationTimeToNumTokens[expirationTime] ?: 0
    expirationTimeToNumTokens[expirationTime] = numTokensAtTime + 1
  }

  fun renew(tokenId: String, currentTime: Int) {
    val existingExpiration = tokenIdToExpirationTime[tokenId]
    if (existingExpiration == null || existingExpiration <= currentTime) {
      return
    }
    val newExpirationTime = currentTime + timeToLive
    tokenIdToExpirationTime[tokenId] = newExpirationTime
    expirationTimeToNumTokens[existingExpiration] = expirationTimeToNumTokens[existingExpiration]!! - 1
    val numTokensAtNewTime = expirationTimeToNumTokens[newExpirationTime] ?: 0
    expirationTimeToNumTokens[newExpirationTime] = numTokensAtNewTime + 1
  }

  fun countUnexpiredTokens(currentTime: Int): Int {
    return expirationTimeToNumTokens.tailMap(currentTime, false).values.sum()
  }

}