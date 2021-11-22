package problems.problem1166

class FileSystem() {

  private val root = File(-1)

  fun createPath(path: String, value: Int): Boolean {
    val components = path.drop(1).split("/")
    var currentFile = root
    for (i in 0 until components.size - 1) {
      val next = currentFile.children[components[i]]
      if (next == null) {
        return false
      }
      currentFile = next
    }
    val toInsert = components[components.size - 1]
    if (currentFile.children.containsKey(toInsert)) {
      return false
    }
    currentFile.children[toInsert] = File(value)
    return true
  }

  fun get(path: String): Int {
    val components = path.drop(1).split("/")
    var currentFile = root
    for (component in components) {
      val next = currentFile.children[component]
      if (next == null) {
        return -1
      }
      currentFile = next
    }
    return currentFile.value
  }

}

data class File(val value: Int, val children: MutableMap<String, File> = mutableMapOf())