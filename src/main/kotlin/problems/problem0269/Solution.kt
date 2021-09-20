package problems.problem0269

import java.util.*

class Solution {
  fun alienOrder(words: Array<String>): String {

    for (i in 0 until words.size - 1) {
      val first = words[i]
      val second = words[i + 1]
      if (first.length > second.length && first.startsWith(second)) {
        return ""
      }
    }

    val letters = mutableMapOf<Char, MutableList<Char>>()
    for (word in words) {
      for (char in word.toCharArray()) {
        letters[char] = mutableListOf<Char>()
      }
    }
    val maxSize = words.map{ it.length }.max()!!
    for (i in 0 until maxSize) {
      addEdges(words, letters, i)
    }

    val temporary = mutableSetOf<Char>()
    val permanent = mutableSetOf<Char>()
    val list = LinkedList<Char>()
    fun hasCycle(currentChar: Char): Boolean {
      if (permanent.contains(currentChar)) {
        return false
      }
      if (temporary.contains(currentChar)) {
        return true
      }
      temporary.add(currentChar)
      val edges = letters[currentChar]!!
      for (edge in edges) {
        val hasCycle = hasCycle(edge)
        if (hasCycle) {
          return true
        }
      }
      temporary.remove(currentChar)
      permanent.add(currentChar)
      list.addFirst(currentChar)
      return false
    }

    for (letter in letters.keys) {
      val hasCycle = hasCycle(letter)
      if (hasCycle) {
        return ""
      }
    }
    return list.joinToString("")
  }

  private fun addEdges(words: Array<String>, letters: Map<Char, MutableList<Char>>, index: Int) {
    val substrings = words
      .filter { it.length >= index + 1 }
      .map { it.substring(0, index + 1) }
    val grouped = mutableMapOf<String, MutableList<Char>>()
    for (substring in substrings) {
      val firstLetters = substring.substring(0, index)
      val groupedLetterList = grouped[firstLetters] ?: mutableListOf<Char>()
      if (groupedLetterList.isEmpty() || groupedLetterList.last() != substring[index]) {
        groupedLetterList.add(substring[index])
      }
      grouped[firstLetters] = groupedLetterList
    }

    for (value in grouped.values) {
      for (i in 0 until value.size - 1) {
        val edges = letters[value[i]]!!
        for (j in i + 1 until value.size) {
          edges.add(value[j])
        }
      }
    }
  }
}

fun main(args: Array<String>) {
  println(Solution().alienOrder(arrayOf("wrt","wrf","er","ett","rftt")))
  println(Solution().alienOrder(arrayOf("z","x","z")))
  println(Solution().alienOrder(arrayOf("zy","zx")))
  println(Solution().alienOrder(arrayOf("abc", "ab")))
}