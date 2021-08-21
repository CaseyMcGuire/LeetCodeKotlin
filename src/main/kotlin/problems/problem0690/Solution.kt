package problems.problem0690

import java.util.*

class Solution {
  fun getImportance(employees: List<Employee?>, id: Int): Int {
    val idToEmployee = mutableMapOf<Int, Employee>()
    val employeesToCheck = ArrayDeque<Int>()
    var importanceSoFar = 0
    for (employee in employees.filterNotNull()) {
      idToEmployee[employee.id] = employee
    }
    employeesToCheck.push(id)
    while (employeesToCheck.isNotEmpty()) {
      val currentEmployeeId = employeesToCheck.pop()
      val currentEmployee = idToEmployee[currentEmployeeId] ?: continue
      importanceSoFar += currentEmployee.importance
      employeesToCheck.addAll(currentEmployee.subordinates)
    }
    return importanceSoFar
  }
}

class Employee {
  var id: Int = 0
  var importance: Int = 0
  var subordinates: List<Int> = listOf()
}