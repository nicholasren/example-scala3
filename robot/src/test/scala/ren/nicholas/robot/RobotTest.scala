package ren.nicholas.robot

import ren.nicholas.robot.cmd.*
import ren.nicholas.robot.directions.Direction._
import ren.nicholas.robot.model.*

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
    robot.execute(PlaceCmd(0, 0, North))

    assertEquals(robot.state.get, State(0, 0, North))
  }

  test("should ignore subsequent Place command") {
    robot.execute(PlaceCmd(0, 0, North))
    robot.execute(PlaceCmd(1, 1, East))

    assertEquals(robot.state.get, State(0, 0, North))
  }

  test("should not set state when initial coordinate is out of boundary") {
    robot.execute(PlaceCmd(6, 7, North))

    assert(robot.state.isEmpty)
  }

  //Rotate
  test("should change to next direction on the left") {
    robot.execute(PlaceCmd(0, 0, North))
    robot.execute(LeftCmd())

    assertEquals(robot.state.get, State(0, 0, West))
  }

  test("should change to next direction on the right") {
    robot.execute(PlaceCmd(0, 0, North))
    robot.execute(RightCmd())

    assertEquals(robot.state.get, State(0, 0, East))
  }

  //Move
  //TODO: when initial state is invalid
  test("should ignore when current state is invalid") {
    robot.execute(MoveCmd())

    assert(robot.state.isEmpty)
  }

  //valid
  test("should change x and y by delta") {
    robot.execute(PlaceCmd(0, 0, East))

    robot.execute(MoveCmd())

    assertEquals(robot.state.get, State(1, 0, East))
  }

  test("should ignore when next step is invalid") {
    robot.execute(PlaceCmd(0, 0, North))

    robot.execute(MoveCmd())

    assertEquals(robot.state.get, State(0, 0, North))
  }

  test("should continue valid commands after invalid") {
    robot.execute(PlaceCmd(0, 0, North))

    robot.execute(MoveCmd()) // ignore
    robot.execute(RightCmd()) // accept
    robot.execute(RightCmd()) // accept
    robot.execute(MoveCmd()) // accept

    assertEquals(robot.state.get, State(0, 1, South))
  }
}
