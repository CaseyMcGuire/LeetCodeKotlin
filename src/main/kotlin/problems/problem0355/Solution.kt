package problems.problem0355

import java.util.*

class Twitter() {

  private var timestamp = 0
  private val userToFollowing = mutableMapOf<Int, MutableSet<Int>>()
  private val userIdToTweets = mutableMapOf<Int, MutableSet<Tweet>>()

  fun postTweet(userId: Int, tweetId: Int) {
    val set = userIdToTweets[userId] ?: mutableSetOf()
    val tweet = Tweet(tweetId, timestamp)
    timestamp++
    set.add(tweet)
    userIdToTweets[userId] = set
  }

  fun getNewsFeed(userId: Int): List<Int> {
    val following = userToFollowing[userId] ?: mutableSetOf()
    val tweets = following.map { userIdToTweets[it] ?: mutableSetOf() }
    val allTweets = TreeSet<Tweet>(compareBy({it.timestamp}))
    tweets.forEach { allTweets.addAll(it) }
    allTweets.addAll(userIdToTweets[userId] ?: TreeSet<Tweet>())
    val recent = mutableListOf<Int>()
    var count = 0
    while (count < 10 && allTweets.isNotEmpty()) {
      recent.add(allTweets.pollLast().id)
      count++
    }
    return recent
  }

  fun follow(followerId: Int, followeeId: Int) {
    val existingFollowing = userToFollowing[followerId] ?: mutableSetOf()
    existingFollowing.add(followeeId)
    userToFollowing[followerId] = existingFollowing
  }

  fun unfollow(followerId: Int, followeeId: Int) {
    val existingFollowing = userToFollowing[followerId]
    if (existingFollowing == null) {
      return
    }
    existingFollowing.remove(followeeId)
  }

}

data class Tweet(val id: Int, val timestamp: Int)