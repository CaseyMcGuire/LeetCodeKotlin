package problems.problem1233

class Solution {
  fun removeSubfolders(folder: Array<String>): List<String> {
    val roots = getRootFolders(folder)
    val allFolders = folder.toMutableSet()
    for (currentFolder in folder) {
      val subfolders = currentFolder.split("/").filter { it.isNotEmpty() }
      var curr = roots[subfolders[0]]!!
      if (curr.isTerminal && subfolders.size > 1) {
        allFolders.remove(currentFolder)
        continue
      }
      for (i in 1 until subfolders.size) {
        val next = subfolders[i]
        curr = curr.subfolders[next]!!
        if (curr.isTerminal && i != subfolders.size - 1) {
          allFolders.remove(currentFolder)
          break
        }
      }
    }
    return allFolders.toList()
  }


  fun getRootFolders(folders: Array<String>): MutableMap<String, Folder> {
    val roots = mutableMapOf<String, Folder>()
    for (folder in folders) {
      val subfolders = folder.split("/").filter { it.isNotEmpty() }
      val first = subfolders[0]
      val rootFolder = roots[first]
      var current = if (rootFolder == null) {
        val newRoot = Folder(first)
        roots[first] = newRoot
        newRoot
      } else {
        roots[first]
      }
      //current!!.isTerminal = subfolders.size == 1

      for (i in 1 until subfolders.size) {
        val currentFolder = subfolders[i]
        var next = current!!.subfolders[currentFolder]

        if (next == null) {
          next = Folder(currentFolder)
          current!!.subfolders[currentFolder] = next
        }
        current = next
      }
      current!!.isTerminal = true
    }
    return roots
  }
}

data class Folder(val name: String, var isTerminal: Boolean = false, val subfolders: MutableMap<String, Folder> = mutableMapOf())
