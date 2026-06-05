package problems.problem1242

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

class Solution {

  fun crawl(startUrl: String, htmlParser: HtmlParser): List<String> {
    val startingHostName = getHostName(startUrl)
    val executor = Executors.newFixedThreadPool(10)
    val visitedOrPending = ConcurrentHashMap.newKeySet<String>()
    val queue = LinkedBlockingQueue<String>()
    val poisonPill = "poison pill"
    val numRunningTasks = AtomicInteger(0)
    visitedOrPending.add(startUrl)
    queue.put(startUrl)
    numRunningTasks.incrementAndGet()
    repeat(10) {
      executor.submit {
        while (true) {
          val next = queue.take()
          if (next == poisonPill) {
            break
          }

          htmlParser.getUrls(next).forEach {
            if (getHostName(it) == startingHostName && visitedOrPending.add(it)) {
              numRunningTasks.incrementAndGet()
              queue.put(it)
            }
          }
          val num = numRunningTasks.decrementAndGet()
          if (num == 0 && queue.isEmpty()) {
            repeat(10) {
              queue.put(poisonPill)
            }
            break
          }
        }
      }
    }

    executor.shutdown()
    executor.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.SECONDS)
    return visitedOrPending.toList()
  }

  private fun getHostName(url: String): String {
    return url.removePrefix("http://")
      .split("/")
      .first()
      .split(":")
      .first()
  }

  interface HtmlParser {
    fun getUrls(url: String): List<String>
  }
}