package problems.problem1603

class ParkingSystem(var big: Int, var medium: Int, var small: Int) {

  fun addCar(carType: Int): Boolean {
    return when (carType) {
      1 -> {
        if (big > 0) {
          big--
          true
        }
        else false
      }
      2 -> {
        if (medium > 0) {
          medium--
          true
        }
        else false
      }
      else -> {
        if (small > 0) {
          small--
          true
        }
        else false
      }
    }
  }

}