package problems.problem0388

import java.util.*

class Solution {
  fun lengthLongestPath(input: String): Int {
    val directories = ArrayDeque<String>()
    var lengthOfLongestPathSoFar = 0
    var i = 0
    var isFile = false
    var currentFileOrDirectoryName = StringBuilder()
    while (i < input.length) {
      if (i == input.length - 1 || input[i] == '\n') {
        if (i == input.length - 1) {
          currentFileOrDirectoryName.append(input[i])
        }
        if (isFile) {
          val directoryPath = directories.joinToString("\\")
          val path = if (directoryPath.isNotEmpty()) directoryPath + "\\" + currentFileOrDirectoryName.toString() else currentFileOrDirectoryName.toString()
          if (lengthOfLongestPathSoFar < path.length) {
            lengthOfLongestPathSoFar = path.length
          }
        }
        else {
          directories.push(currentFileOrDirectoryName.toString())
        }

        isFile = false
        currentFileOrDirectoryName = StringBuilder()

        i += 1
        var numDirectories = 0
        while (i < input.length && input[i] == '\t') {
          numDirectories += 1
          i += 1
        }

        for (i in 0 until directories.size - numDirectories) {
          directories.pop()
        }

      }
      else {
        if (input[i] == '.') {
          isFile = true
        }
        currentFileOrDirectoryName.append(input[i])
        i++
      }
    }
    return lengthOfLongestPathSoFar
  }
}

fun main(args: Array<String>) {
  println(Solution().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"))
  println(Solution().lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt"))
}