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

  test("delta") {
    assertEquals(N.deltaX, 0)
    assertEquals(N.deltaY, -1)

    assertEquals(S.deltaX, 0)
    assertEquals(S.deltaY, 1)

    assertEquals(E.deltaX, 1)
    assertEquals(E.deltaY, 0)


    assertEquals(W.deltaX, -1)
    assertEquals(W.deltaY, 0)
  }
}
