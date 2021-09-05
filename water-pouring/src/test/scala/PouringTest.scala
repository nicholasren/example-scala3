class PouringTest extends munit.FunSuite {
  test("solution") {
    val testData = Map(
      (3, 5) -> 4,
      (9, 4) -> 6
    )

    testData.foreach((glasses, target) => {
      val pouring = Pouring(Vector(glasses._1, glasses._2))
      val solution = pouring.solutions(target).toList

      assert(solution.headOption.isDefined)
      assert(solution.head.endState.find(_ == target).isDefined)
    })

  }
}