package problems.problem0591

import java.util.*

class Solution {
  fun isValid(code: String): Boolean {
    val openedTags = ArrayDeque<String>()
    var i = 0
    val cData = "![CDATA["
    val cDataCloseTag = "]]>"

    fun getTagName(): String? {
      val builder = StringBuilder()
      while (true) {
        if (i == code.length) {
          return null
        }

        if (code[i] == '>') {
          break
        }

        val c = code[i]
        builder.append(c)
        if (!Character.isUpperCase(c) || !Character.isLetter(c)) {
          return null
        }
        i++
      }
      i++
      if (builder.length == 0 || builder.length > 9) {
        return null
      }
      return builder.toString()
    }

    if (code[0] == '<') {
      i++
    }
    else {
      return false
    }

    val openedTag = getTagName()
    if (openedTag == null) {
      return false
    }
    openedTags.push(openedTag)

    while (i < code.length) {
      if (code[i] == '<') {
        i++
        if (i == code.length) {
          return false
        }

        // c data tag
        if (code[i] == '!') {
          val end = i + cData.length
          if (end >= code.length) {
            return false
          }
          val cDataStart = code.substring(i, i + cData.length)
          if (cDataStart != cData) {
            return false
          }
          i += cData.length
          while (true) {
            if (i == code.length) {
              return false
            }
            val endLength = i + cDataCloseTag.length
            if (endLength > code.length) {
              return false
            }
            val ending = code.substring(i, i + cDataCloseTag.length)
            if (ending == cDataCloseTag) {
              i += ending.length
              break
            }
            i++
          }
        } else if (code[i] == '/') {
          i++
          val tagName = getTagName()
          if (tagName == null || openedTags.isEmpty() || tagName != openedTags.pop()) {
            return false
          }
          if (openedTags.isEmpty()) {
            return i == code.length
          }
        } else {
          val tagName = getTagName()
          if (tagName == null) {
            return false
          }
          openedTags.push(tagName)
        }
      } else {
        i++
      }
    }
    return openedTags.isEmpty()
  }
}