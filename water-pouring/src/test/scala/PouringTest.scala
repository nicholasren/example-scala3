import org.scalatest.funsuite.AnyFunSuite

class PouringTest extends AnyFunSuite {
  test("shortestSolution") {
    val testData = Map(
      (3, 5) -> 4,
      (9, 4) -> 6
    )

    testData.foreach((glasses, target) => {
      val pouring = Pouring(Vector(glasses._1, glasses._2))

      val solution = pouring.shortestSolution(target)

      assert(solution.isDefined)
      assert(solution.get.endState.contains(target))
    })
  }

  test("solution") {
    val testData = Map(
      (3, 5) -> 4,
      (9, 4) -> 6
    )

    testData.foreach((glasses, target) => {
      val pouring = Pouring(Vector(glasses._1, glasses._2))

      val solutions = pouring.solutions(target)

      assert(solutions.nonEmpty)
      assert(solutions.head.endState.contains(target))
    })
  }
}