package problems.problem0715

class RangeModule() {


  private val ranges = sortedSetOf<Range>(compareBy { it.left })

  fun addRange(left: Int, right: Int) {
    val newRange = Range(left, right)
    val rangesToRemove = getOverlappingRanges(newRange)

    ranges.removeAll(rangesToRemove)
    var combinedRange = newRange
    for (range in rangesToRemove) {
      combinedRange = combinedRange.combine(range)
    }
    ranges.add(combinedRange)
  }

  fun queryRange(left: Int, right: Int): Boolean {
    val curRange = Range(left + 1, right)
    val lesserRange = ranges.lower(curRange) ?: return false
    return lesserRange.contains(left) && lesserRange.contains(right - 1)
  }

  fun removeRange(left: Int, right: Int) {
    val curRange = Range(left, right)
    val rangesToRemove = getOverlappingRanges(curRange)
    val sortedRangesToRemove = rangesToRemove.sortedBy { it.left }
    ranges.removeAll(rangesToRemove)

    val smallest = sortedRangesToRemove.firstOrNull()
    if (smallest != null && smallest.left < curRange.left) {
      ranges.add(Range(smallest.left, curRange.left))
    }

    val largest = sortedRangesToRemove.lastOrNull()
    if (largest != null && largest.right > curRange.right) {
      ranges.add(Range(curRange.right, largest.right))
    }
  }

  private fun getOverlappingRanges(range: Range): List<Range> {
    val rangesToRemove = mutableListOf<Range>()
    val largerRanges = ranges.tailSet(range, true)
    val smallerRanges = ranges.headSet(range, true).descendingSet()

    for (otherRange in largerRanges) {
      if (range.overlaps(otherRange)) {
        rangesToRemove.add(otherRange)
      }
      else {
        break
      }
    }

    for (otherRange in smallerRanges) {
      if (range.overlaps(otherRange)) {
        rangesToRemove.add(otherRange)
      }
      else {
        break
      }
    }

    return rangesToRemove
  }

}

data class Range(val left: Int, val right: Int) {
  fun contains(num: Int): Boolean {
    return num in left until right
  }

  fun overlaps(other: Range): Boolean {
    val thisRange = this.left until this.right
    val otherRange = other.left until other.right
    return left in otherRange
        || right in otherRange
        || other.left in thisRange
        || other.right in thisRange
  }

  fun combine(other: Range): Range {
    val largerLeft = Math.min(this.left, other.left)
    val largerRight = Math.max(this.right, other.right)
    return Range(largerLeft, largerRight)
  }
}