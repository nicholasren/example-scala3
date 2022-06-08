package ren.nicholas.robot.directions

import Facing._

class FacingTest extends munit.FunSuite {
  test("left") {
    Map(
      N -> W,
      E -> N,
      S -> E,
      W -> S
    ).foreach((from, to) => assertEquals(from.left, to))
  }

  test("right") {
    Map(
      N -> E,
      E -> S,
      S -> W,
      W -> N
    ).foreach((from, to) => assertEquals(from.right, to))
  }
}
