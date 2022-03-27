package problems.problem1096

class Solution {
  fun braceExpansionII(expression: String): List<String> {
    var i = 0

    fun recurse(): List<String> {
      val strs = mutableListOf<String>()
      var strsInExpression = mutableListOf<String>()
      while (i < expression.length && expression[i] != '}') {
        if (expression[i] == ',') {
          for (str in strsInExpression) {
            strs.add(str)
          }
          strsInExpression = mutableListOf()
          i++
        }
        else if (expression[i] == '{') {
          i++
          val nestedExpressions = recurse()
          i++
          val combined = mutableListOf<String>()
          if (strsInExpression.isNotEmpty()) {
            for (str in strsInExpression) {
              for (nestedExpression in nestedExpressions) {
                combined.add("${str}${nestedExpression}")
              }
            }
          }
          else {
            combined.addAll(nestedExpressions)
          }
          strsInExpression = combined
        }
        else {
          val newList = mutableListOf<String>()
          if (strsInExpression.isNotEmpty()) {
            for (str in strsInExpression) {
              newList.add(str + expression[i])
            }
          }
          else {
            newList.add(expression[i].toString())
          }
          strsInExpression = newList
          i++
        }
      }
      return strs + strsInExpression
    }

    return recurse().toSet().toList().sorted()
  }

}