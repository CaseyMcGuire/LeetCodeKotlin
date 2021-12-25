package problems.problem0588

class FileSystem() {

  val root = Folder()

  fun ls(path: String): List<String> {
    val baseFile = getFile(path)
    return when(baseFile) {
      is Folder -> (baseFile.folders.keys union baseFile.files.keys).toList().sorted()
      is File -> listOf(baseFile.name)
    }
  }

  fun mkdir(path: String) {
    val folders = getFilePath(path)
    var curFolder = root
    for (folder in folders) {
      val nextFolder = curFolder.folders[folder] ?: Folder()
      curFolder.folders[folder] = nextFolder
      curFolder = nextFolder
    }
  }

  fun addContentToFile(filePath: String, content: String) {
    val path = getFilePath(filePath)
    val fileName = path[path.size - 1]
    val containingFolder = getFolder(path.dropLast(1))
    val file = containingFolder.files[fileName]
    if (file == null) {
      val created = File(fileName, content)
      containingFolder.files[fileName] = created
    }
    else {
      file.content += content
    }
  }

  fun readContentFromFile(filePath: String): String {
    val file = getFile(filePath)
    return when (file) {
      is File -> file.content
      is Folder -> throw RuntimeException()
    }
  }

  private fun getFile(path: String): BaseFile {
    val filePath = getFilePath(path)
    if (filePath.isEmpty()) {
      return root
    }
    var cur = root
    for (i in 0 until filePath.size - 1) {
      val file = filePath[i]
      cur = cur.folders[file]!!
    }
    val file = cur.files[filePath[filePath.size -1]]
    if (file != null) {
      return file
    }
    else {
      return cur.folders[filePath[filePath.size - 1]]!!
    }
  }

  private fun getFolder(path: List<String>): Folder {
    var cur = root
    for (p in path) {
      cur = cur.folders[p]!!
    }
    return cur!!
  }

  private fun getFilePath(path: String): List<String> {
    return path.split("/").filter { it.isNotEmpty() }
  }

}
sealed class BaseFile
data class Folder(val folders: MutableMap<String, Folder> = mutableMapOf(), val files: MutableMap<String, File> = mutableMapOf()): BaseFile()
data class File(val name: String, var content: String): BaseFile()