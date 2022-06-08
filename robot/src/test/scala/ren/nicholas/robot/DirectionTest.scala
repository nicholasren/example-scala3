package ren.nicholas.robot

import ren.nicholas.robot.model.Direction.*
import org.scalatest.funsuite.AnyFunSuite


class DirectionTest extends AnyFunSuite {
  test("left") {
    Map(
      North -> West,
      East -> North,
      South -> East,
      West -> South
    ).foreach((from, to) => assert(from.left == to))
  }

  test("right") {
    Map(
      North -> East,
      East -> South,
      South -> West,
      West -> North
    ).foreach((from, to) => assert(from.right == to))
  }

  test("delta") {
    assert(North.deltaX == 0)
    assert(North.deltaY == -1)

    assert(South.deltaX == 0)
    assert(South.deltaY == 1)

    assert(East.deltaX == 1)
    assert(East.deltaY == 0)


    assert(West.deltaX == -1)
    assert(West.deltaY == 0)
  }
}
