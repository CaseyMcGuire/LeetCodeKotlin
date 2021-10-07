package problems.problem1146

class SnapshotArray(length: Int) {

  val snapIdToValues: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()
  var currentSnapId = 0
  var currentArray: MutableMap<Int, Int> = mutableMapOf()

  fun set(index: Int, `val`: Int) {
    currentArray[index] = `val`
  }

  fun snap(): Int {
    snapIdToValues[currentSnapId] = currentArray.toMutableMap()
    currentSnapId++
    return currentSnapId - 1
  }

  fun get(index: Int, snap_id: Int): Int {
    val copy = snapIdToValues[snap_id]
    return copy!![index] ?: 0
  }

}