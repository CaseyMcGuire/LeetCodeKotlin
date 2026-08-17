package problems.problem2502

class Allocator(n: Int) {

  private val valueToIndices = mutableMapOf<Int, TreeSet<Int>>()

  init {
    val indices = TreeSet<Int>()
    for (i in 0 until n) {
      indices.add(i)
    }
    valueToIndices[0] = indices
  }

  fun allocate(size: Int, mID: Int): Int {
    val freeIndices = valueToIndices[0]!!
    var curBlock = mutableListOf<Int>()
    for (index in freeIndices) {
      if (curBlock.lastOrNull() == index - 1) {
        curBlock.add(index)
      }
      else {
        curBlock = mutableListOf<Int>(index)
      }

      if (curBlock.size == size) {
        break
      }
    }

    if (curBlock.size != size) {
      return -1
    }

    val midIndices = valueToIndices.getOrPut(mID) { TreeSet<Int>() }
    for (index in curBlock) {
      freeIndices.remove(index)
      midIndices.add(index)
    }
    return curBlock.first()
  }

  fun freeMemory(mID: Int): Int {
    val indicesToFree = valueToIndices[mID]
      ?: return 0
    valueToIndices[mID] = TreeSet<Int>()
    val freeIndices = valueToIndices[0]!!

    for (index in indicesToFree) {
      freeIndices.add(index)
    }
    return indicesToFree.size
  }
}