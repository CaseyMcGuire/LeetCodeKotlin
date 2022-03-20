package problems.problem0815

import java.util.*

class Solution {
  fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
    if (source == target) {
      return 0
    }
    routes.forEach { it.sort() }
    val stopToRoutes = mutableMapOf<Int, MutableSet<Int>>()
    for (i in routes.indices) {
      val stops = routes[i]
      for (stop in stops) {
        val existingRoutes = stopToRoutes[stop] ?: mutableSetOf()
        existingRoutes.add(i)
        stopToRoutes[stop] = existingRoutes
      }
    }

    val queue = LinkedList<BusDistance>()
    val sourceRoutes = stopToRoutes[source] ?: mutableSetOf()
    val visitedRoutes = mutableSetOf<Int>()
    val initialRoutes = stopToRoutes[source] ?: mutableSetOf()
    for (routeId in initialRoutes) {
      queue.addLast(BusDistance(routeId, 1))
    }


    while (queue.isNotEmpty()) {
      val curDistance = queue.removeFirst()
      val curRouteId = curDistance.routeId
      val stopsInRoute = routes[curRouteId]
      val distanceSoFar = curDistance.distance

      if (!visitedRoutes.add(curRouteId)) {
        continue
      }

      if (stopsInRoute.containsNum(target)) {
        return distanceSoFar
      }

      for (stop in stopsInRoute) {
        val routeIds = stopToRoutes[stop] ?: mutableSetOf()
        for (id in routeIds) {
          queue.addLast(BusDistance(id, distanceSoFar + 1))
        }
      }
    }

    return -1
  }

  private fun IntArray.containsNum(num: Int): Boolean {
    return Arrays.binarySearch(this, num) >= 0
  }
}

data class BusDistance(val routeId: Int, val distance: Int)