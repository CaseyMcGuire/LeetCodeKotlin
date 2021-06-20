package problems.problem0127

import java.util.*


class Solution {
  fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    val allWords = if (wordList.contains(beginWord)) {
      wordList
    }
    else {
      wordList + beginWord
    }
    var currentLevel = LinkedList<String>()
    var nextLevel = LinkedList<String>()
    val visited = hashSetOf(beginWord)
    currentLevel.addLast(beginWord)
    var currentDepth = 1
    while (currentLevel.isNotEmpty()) {
      val current = currentLevel.removeFirst()
      if (current == endWord) {
        return currentDepth
      }
      val edges = allWords.filter {
        it != current && it.differByOneLetter(current) && !visited.contains(it)
      }
      visited.addAll(edges)
      nextLevel.addAll(edges)
      if (currentLevel.isEmpty()) {
        currentLevel = nextLevel
        nextLevel = LinkedList()
        currentDepth++
      }
    }
    return 0
  }



  private fun String.differByOneLetter(other: String): Boolean {
    if (other.length != this.length) {
      return false
    }
    var foundDifferingLetter = false
    this.zip(other).forEach {
      val (first, second) = it
      val areEqual = first == second
      if (!areEqual && foundDifferingLetter) {
        return false
      }
      if (!areEqual) {
        foundDifferingLetter = true
      }
    }
    return foundDifferingLetter
  }
}

fun main(args: Array<String>) {
  //println(Solution().ladderLength("hit" ,"cog", mutableListOf("hot","dot","dog","lot","log","cog")))
  //println(Solution().ladderLength("leet", "code", mutableListOf("lest","leet","lose","code","lode","robe","lost")))
  println(Solution().ladderLength("hit" ,"cog", mutableListOf("hot","dot","dog","lot","log")))
}