package problems.problem1236

import java.util.*

class Solution {
  fun crawl(startUrl:String, htmlParser:HtmlParser):List<String> {
    val visited = mutableSetOf<String>()
    val hostname = getHostName(startUrl)
    val pendingUrls = ArrayDeque<String>()
    pendingUrls.push(startUrl)
    while (pendingUrls.isNotEmpty()) {
      val currentUrl = pendingUrls.pop()
      if (!visited.add(currentUrl)) {
        continue
      }
      val links = htmlParser.getUrls(currentUrl).filter { !visited.contains(it) && getHostName(it) == hostname }
      for (link in links) {
        pendingUrls.push(link)
      }
    }
    return visited.toList()
  }

  private fun getHostName(url: String): String {
    val withoutHttp = url.substring(7, url.length)
    val indexOfSlash = withoutHttp.indexOf("/")
    if (indexOfSlash == -1) {
      return withoutHttp
    }
    return withoutHttp.substring(0, indexOfSlash)
  }
}

class HtmlParser {
  fun getUrls(url:String):List<String> {return emptyList()}
}