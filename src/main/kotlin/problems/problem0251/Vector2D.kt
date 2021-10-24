package problems.problem0251

class Vector2D(vec: Array<IntArray>) {
  var vecIndex = 0
  var innerIndex = 0
  val vecFiltered = vec.filter { it.isNotEmpty() }

  fun next(): Int {
    val elem = vecFiltered[vecIndex][innerIndex]
    innerIndex++
    if (innerIndex == vecFiltered[vecIndex].size) {
      vecIndex++
      innerIndex = 0
    }
    return elem
  }

  fun hasNext(): Boolean {
    return vecIndex < vecFiltered.size
  }

}