package problems.problem0174

class Solution {
  fun calculateMinimumHP(dungeon: Array<IntArray>): Int {
    val healthSum = Array<IntArray>(dungeon.size) { IntArray(dungeon[0].size) }
    healthSum[0][0] = dungeon[0][0]

    for (i in dungeon.indices.reversed()) {
      for (j in dungeon[i].indices.reversed()) {
        if (i == dungeon.size - 1 && j == dungeon[0].size - 1) {
          healthSum[i][j] = if (dungeon[i][j] < 0) Math.abs(dungeon[i][j]) + 1 else 1
          continue
        }
        val healthToMove = if (i == dungeon.size - 1) {
          healthSum[i][j + 1]
        }
        else if (j == dungeon[0].size - 1) {
          healthSum[i + 1][j]
        }
        else {
          val right = healthSum[i][j + 1]
          val down = healthSum[i + 1][j]
          Math.min(right, down)
        }

        val sum = healthToMove - dungeon[i][j]
        if (sum <= 0) {
          healthSum[i][j] = 1
        }
        else {
          healthSum[i][j] = sum
        }

      }
    }

    return healthSum[0][0]
  }
}