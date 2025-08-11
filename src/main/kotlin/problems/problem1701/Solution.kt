package problems.problem1701

import java.util.LinkedList

class Solution {
  fun averageWaitingTime(customers: Array<IntArray>): Double {
    if (customers.isEmpty()) {
      return 0.0
    }
    var totalWaitingTime = 0L
    var curTime = 0L
    val waitingOrders = LinkedList<IntArray>()
    val pendingCustomers = customers.toLinkedList()
    while (waitingOrders.isNotEmpty() || pendingCustomers.isNotEmpty()) {
      if (waitingOrders.isEmpty() && curTime < pendingCustomers.first().getArrivalTime()) {
        curTime = pendingCustomers.first().getArrivalTime().toLong()
      }

      while (pendingCustomers.isNotEmpty() &&
        pendingCustomers.first().getArrivalTime() <= curTime) {
        waitingOrders.addLast(pendingCustomers.removeFirst())
      }

      val nextOrder = waitingOrders.removeFirst()
      curTime += nextOrder.getOrderPrepTime()
      val waitingTimeForOrder = curTime - nextOrder.getArrivalTime()
      totalWaitingTime += waitingTimeForOrder
    }

    return totalWaitingTime.toDouble() / customers.size.toDouble()
  }

  private fun IntArray.getArrivalTime(): Int {
    return this[0]
  }

  private fun IntArray.getOrderPrepTime(): Int {
    return this[1]
  }

  private fun Array<IntArray>.toLinkedList(): LinkedList<IntArray> {
    val linkedList = LinkedList<IntArray>()
    for (arr in this) {
      linkedList.add(arr)
    }
    return linkedList
  }
}