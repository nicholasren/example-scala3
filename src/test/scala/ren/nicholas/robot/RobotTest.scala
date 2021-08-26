package ren.nicholas.robot

import ren.nicholas.robot.cmd.*
import ren.nicholas.robot.model.*
import ren.nicholas.robot.model.directions.*

class RobotTest extends munit.FunSuite {
  var table: Table = null
  var robot: Robot = null

  override def beforeEach(context: BeforeEach) = {
    table = Table(5, 5)
    robot = Robot()
    robot.on(table)
  }

  //Placing
  test("should set initial state") {
    var cmd = PlaceCmd(0, 0, North)

    robot.execute(cmd)

    assertEquals(robot.state, State(0, 0, North))
  }

  test("should not set state when initial coordinate is out of boundary") {
    var cmd = PlaceCmd(6, 7, North)

    robot.execute(cmd)

    assertEquals(robot.state, null)
  }

  //Rotate
  test("should change to next direction on the left") {
    var place = PlaceCmd(0, 0, North)
    var left = LeftCmd()

    robot.execute(place)
    robot.execute(left)

    assertEquals(robot.state, State(0, 0, West))
  }

  test("should change to next direction on the right") {
    var place = PlaceCmd(0, 0, North)
    var right = RightCmd()

    robot.execute(place)
    robot.execute(right)

    assertEquals(robot.state, State(0, 0, East))
  }

  //Move
  //TODO: when initial state is invalid
  test("should ignore when current state is invalid") {
    robot.execute(MoveCmd())

    assert(robot.state == null)
  }

  //valid
  test("should change x and y by delta") {
    robot.execute(PlaceCmd(0, 0, East))

    robot.execute(MoveCmd())

    assert(robot.state == State(1, 0, East))
  }
}
