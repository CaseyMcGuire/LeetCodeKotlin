package problems.problem0365

class Solution {
  fun canMeasureWater(jug1Capacity: Int, jug2Capacity: Int, targetCapacity: Int): Boolean {
    val visited = mutableSetOf<JugState>()

    fun dfs(jug1Level: Int, jug2Level: Int): Boolean {
      if (jug1Level == targetCapacity || jug2Level == targetCapacity || jug2Level + jug1Level == targetCapacity) {
        return true
      }
      val jugState = JugState(jug1Level, jug2Level)
      if (!visited.add(jugState)) {
        return false
      }

      val canMeasure = dfs(0, jug2Level) || // empty jug 1
          dfs(jug1Level, 0) || // empty jug 2
          dfs(jug1Capacity, jug2Level) ||
          dfs(jug1Level, jug2Capacity)

      if (canMeasure) {
        return true
      }
      // pour from 2 to 1
      if (jug1Capacity != jug1Level && jug2Level > 0) {
        val difference = jug1Capacity - jug1Level
        val amountPoured = Math.min(jug2Level, difference)
        if (dfs(jug1Level + amountPoured, jug2Level - amountPoured)) {
          return true
        }
      }

      if (jug2Capacity != jug2Level && jug1Level > 0) {
        val difference = jug2Capacity - jug2Level
        val amountPoured = Math.min(jug1Level, difference)
        if (dfs(jug1Level - amountPoured, jug2Level + amountPoured)) {
          return true
        }
      }
      return false
    }
    return dfs(0, 0)
  }
}

data class JugState(val jug1Level: Int, val jug2Level: Int)