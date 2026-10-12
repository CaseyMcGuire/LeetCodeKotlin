package problems.problem2353

import java.util.TreeSet

class FoodRatings(names: Array<String>, cuisines: Array<String>, ratings: IntArray) {

  private val nameToFood = mutableMapOf<String, Food>()
  private val cuisineToFood = mutableMapOf<String, TreeSet<Food>>()

  init {
    for (i in names.indices) {
      addFood(names[i], cuisines[i], ratings[i])
    }
  }

  fun changeRating(name: String, newRating: Int) {
    val food = nameToFood[name]
      ?: error("No food with name: $name")

    cuisineToFood[food.cuisine]!!.remove(food)
    addFood(name, food.cuisine, newRating)
  }

  private fun addFood(name: String, cuisine: String, rating: Int) {
    val food = Food(name, cuisine, rating)
    nameToFood[name] = food
    val sortedFood = cuisineToFood.getOrPut(cuisine) {
      TreeSet<Food>(
        compareBy<Food> { it.rating }
          .thenByDescending { it.name }
      )
    }
    sortedFood.add(food)
  }

  fun highestRated(cuisine: String): String {
    val sortedFood = cuisineToFood[cuisine]
      ?: error("No food with cuisine: $cuisine")
    return sortedFood.last().name
  }

  private data class Food(val name: String, val cuisine: String, val rating: Int)

}