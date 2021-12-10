package problems.problem1237

class Solution {
  fun findSolution(customfunction:CustomFunction, z:Int): List<List<Int>> {
    val answers = mutableListOf<List<Int>>()
    for (i in 1..1000) {
      for (j in 1..1000) {
        val value = customfunction.f(i, j)
        if (value > z) {
          break
        }
        else if (value == z) {
          answers.add(listOf(i, j))
        }
      }
    }
    return answers
  }
}

 interface CustomFunction {
   fun f(x:Int, y:Int):Int
}