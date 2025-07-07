package problems.problem3443

class Solution {
  fun maxDistance(s: String, k: Int): Int {
    val directionToFrequency = mutableMapOf<Char, Int>()
    var maxSoFar: Int? = null
    for (c in s) {
      directionToFrequency.merge(c, 1) { cur, acc -> cur + acc }

      var remainingChanges = k
      val numNorth = directionToFrequency['N'] ?: 0
      val numSouth = directionToFrequency['S'] ?: 0
      var (higherVertical, lowerVertical) = if (numNorth < numSouth) {
        Pair(numSouth, numNorth)
      }
      else {
        Pair(numNorth, numSouth)
      }

      var numChanges = Math.min(lowerVertical, remainingChanges)
      lowerVertical -= numChanges
      higherVertical += numChanges
      remainingChanges -= numChanges



      val numEast = directionToFrequency['E'] ?: 0
      val numWest = directionToFrequency['W'] ?: 0
      var (higherHorizontal, lowerHorizontal) = if (numEast < numWest) {
        Pair(numWest, numEast)
      }
      else {
        Pair(numEast, numWest)
      }

      numChanges = Math.min(lowerHorizontal, remainingChanges)
      lowerHorizontal -= numChanges
      higherHorizontal += numChanges

      val distanceSoFar = higherVertical - lowerVertical + higherHorizontal - lowerHorizontal
      if (maxSoFar == null || distanceSoFar > maxSoFar) {
        maxSoFar = distanceSoFar
      }
    }
    return maxSoFar!!
  }
}