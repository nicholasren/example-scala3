package ren.nicholas.robot.directions

import Direction._

class DirectionTest extends munit.FunSuite {
  test("left") {
    Map(
      North -> West,
      East -> North,
      South -> East,
      West -> South
    ).foreach((from, to) => assertEquals(from.left, to))
  }

  test("right") {
    Map(
      North -> East,
      East -> South,
      South -> West,
      West -> North
    ).foreach((from, to) => assertEquals(from.right, to))
  }

  test("delta") {
    assertEquals(North.deltaX, 0)
    assertEquals(North.deltaY, -1)

    assertEquals(South.deltaX, 0)
    assertEquals(South.deltaY, 1)

    assertEquals(East.deltaX, 1)
    assertEquals(East.deltaY, 0)


    assertEquals(West.deltaX, -1)
    assertEquals(West.deltaY, 0)
  }
}
