package problems.problem0609

class Solution {
  fun findDuplicate(paths: Array<String>): List<List<String>> {
    val fileInfos = mutableListOf<FileInfo>()
    for (path in paths) {
      val curInfos = getFileInfo(path)
      fileInfos.addAll(curInfos)
    }

    val matchingContent = mutableMapOf<String, MutableSet<String>>()
    for (fileInfo in fileInfos) {
      val fullName = fileInfo.getFullName()
      val set = matchingContent[fileInfo.content] ?: mutableSetOf()
      set.add(fullName)
      matchingContent[fileInfo.content] = set
    }

    return matchingContent.values.filter { it.size > 1 }.map { it.toList() }
  }

  private fun getFileInfo(path: String): List<FileInfo> {
    val fileInfos = mutableListOf<FileInfo>()
    val pathAndFiles= path.split(" ")
    val path = pathAndFiles[0]
    val files = pathAndFiles.toList().subList(1, pathAndFiles.size)
    for (file in files) {
      val fileNameAndContent = file.split("(")
      val fileName = fileNameAndContent[0]
      val content = fileNameAndContent[1].substring(0, fileNameAndContent[1].length - 1)
      fileInfos.add(FileInfo(path, fileName, content))
    }
    return fileInfos
  }
}



data class FileInfo(val path: String, val name: String, val content: String) {
  fun getFullName(): String {
    return "$path/$name"
  }
}