package problems.problem3508

class Router(private val memoryLimit: Int) {

  private val queue = ArrayDeque<Packet>()
  private val pending = mutableSetOf<Packet>()
  private val destinationToPackets = mutableMapOf<Int, ArrayDeque<Packet>>()

  fun addPacket(source: Int, destination: Int, timestamp: Int): Boolean {

    val packet = Packet(source, destination, timestamp)
    if (pending.contains(packet)) {
      return false
    }
    pending.add(packet)
    queue.addLast(packet)
    val packets = destinationToPackets.getOrPut(destination) { ArrayDeque<Packet>() }
    packets.addLast(packet)

    if (queue.size > memoryLimit) {
      forwardPacket()
    }
    return true
  }

  fun forwardPacket(): IntArray {
    if (queue.isEmpty()) {
      return intArrayOf()
    }

    val toRemove = queue.removeFirst()
    pending.remove(toRemove)

    val packets = destinationToPackets[toRemove.destination]
      ?: error("No tree for packet: $toRemove")
    packets.removeFirst()

    return toRemove.toArray()
  }

  fun getCount(destination: Int, startTime: Int, endTime: Int): Int {
    val packets = destinationToPackets[destination]
      ?: return 0

    if (packets.isEmpty() || packets.first().timestamp > endTime || packets.last().timestamp < startTime) {
      return 0
    }
    val leftIndex = packets.findLowerBound(startTime)
    val rightIndex = packets.findUpperBound(endTime)
    return rightIndex - leftIndex
  }

  private fun ArrayDeque<Packet>.findLowerBound(timestamp: Int): Int {
    return this.findBoundaryIndex(timestamp, true)
  }

  private fun ArrayDeque<Packet>.findUpperBound(timestamp: Int): Int {
    return this.findBoundaryIndex(timestamp, false)
  }

  private fun ArrayDeque<Packet>.findBoundaryIndex(timestamp: Int, findLeftMost: Boolean): Int {
    var low = 0
    var high = this.size - 1
    while (low <= high) {
      val middle = ((low.toLong() + high.toLong()) / 2L).toInt()
      val element = this[middle]
      if (element.timestamp == timestamp) {
        if (findLeftMost && this.getOrNull(middle - 1)?.timestamp == timestamp) {
          high = middle - 1
        }
        else if (!findLeftMost && this.getOrNull(middle + 1)?.timestamp == timestamp) {
          low = middle + 1
        }
        else {
          return if (findLeftMost) middle else middle + 1
        }
      }
      else if (timestamp > element.timestamp) {
        low = middle + 1
      }
      else {
        high = middle - 1
      }
    }
    return low
  }

  private data class Packet(val source: Int, val destination: Int, val timestamp: Int) {
    fun toArray(): IntArray {
      return intArrayOf(source, destination, timestamp)
    }
  }

}