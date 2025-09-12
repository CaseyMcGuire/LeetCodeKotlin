package problems.problem2402

import java.util.PriorityQueue

class Solution {
  fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
    val availableRooms = PriorityQueue<Int>()
    for (i in 0 until n) {
      availableRooms.add(i)
    }
    val roomToNumMeetings = mutableMapOf<Int, Int>()
    val runningMeetings = PriorityQueue<Meeting>(compareBy({ it.endTime }, { it.room }))
    val pendingMeetings = PriorityQueue<IntArray>(compareBy({ it.getStartTime() }))
    meetings.sortBy { it.getStartTime() }

    for (meeting in meetings) {

      while (runningMeetings.isNotEmpty() && runningMeetings.peek().endTime <= meeting.getStartTime()) {
        val endedMeeting = runningMeetings.poll()
        availableRooms.add(endedMeeting.room)
      }

      if (availableRooms.isEmpty()) {
        val nextEndingMeeting = runningMeetings.poll()
        val room = nextEndingMeeting.room
        roomToNumMeetings.merge(room, 1) { old, new -> old + new }
        runningMeetings.add(Meeting(room, nextEndingMeeting.endTime + meeting.getMeetingLength()))
      }
      else {
        val nextRoom = availableRooms.poll()
        roomToNumMeetings.merge(nextRoom, 1) { old, new -> old + new }
        runningMeetings.add(Meeting(nextRoom, meeting.getEndTime().toLong()))
      }
    }

    return roomToNumMeetings.entries.sortedBy { it.key }.maxBy { it.value }.key
  }

  private fun IntArray.getStartTime(): Int {
    return this[0]
  }

  private fun IntArray.getEndTime(): Int {
    return this[1]
  }

  private fun IntArray.getMeetingLength(): Int {
    return this.getEndTime() - this.getStartTime()
  }
}

data class Meeting(val room: Int, val endTime: Long)