class PouringTest extends munit.FunSuite {
  test("solution") {
    val pouring = Pouring(Vector(3, 5))

    val solution = pouring.solutions(4).toList

    assertEquals(solution.head.endState(1), 4)
  }
}