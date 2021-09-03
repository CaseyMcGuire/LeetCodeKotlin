package problems.problem0406

class Solution {
  fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
    val queue = arrayOfNulls<Person?>(people.size)
    val peopleList =
      people.map { Person(it[0], it[1]) }
        .sortedWith(compareBy( {it.height}, {it.tallerPeopleInFront} ))
    for (person in peopleList) {
      var numTallerPeoplePassed = 0
      var i = 0
      while (true) {
        if (numTallerPeoplePassed == person.tallerPeopleInFront && queue[i] == null) {
          queue[i] = person
          break
        }
        else if (numTallerPeoplePassed == person.tallerPeopleInFront) {
          // keep iterating until we find the next open spot
        }
        else if (queue[i] == null || queue[i]!!.height >= person.height) {
          numTallerPeoplePassed++
        }
        i++
      }
    }
    return queue.map {intArrayOf(it!!.height, it!!.tallerPeopleInFront)}.toTypedArray()
  }
}

data class Person(val height: Int, val tallerPeopleInFront: Int)
