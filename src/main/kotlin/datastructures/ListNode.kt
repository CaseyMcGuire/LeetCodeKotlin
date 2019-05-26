package datastructures

class ListNode(var `val`: Int) {
  var next: ListNode? = null


  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is ListNode) return false
    if (other.`val` != this.`val`) return false
    return this.next == other.next
  }

  override fun toString(): String {
    if (this.next != null) {
      return this.`val`.toString() + "->" + this.next.toString()
    }
    return this.`val`.toString()
  }

  companion object {
    fun create(vararg nums: Int): ListNode? {

      var head: ListNode? = null
      var prev: ListNode? = null
      nums.forEach {
        if (head == null) {
          head = ListNode(it)
          prev = head
        }
        else {
          prev!!.next = ListNode(it)
          prev = prev!!.next
        }
      }
      return head
    }
  }
}