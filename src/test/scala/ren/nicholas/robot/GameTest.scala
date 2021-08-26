package ren.nicholas.robot

import ren.nicholas.robot.cmd.PlaceCmd
import ren.nicholas.robot.model.Game
import ren.nicholas.robot.model.directions.East

class GameTest extends munit.FunSuite {
  var game: Game = null

  override def beforeEach(context: BeforeEach) = {
    game = Game(5, 5)
  }

  test("should print table") {
    val expected =
      """
        |O O O O O
        |O O O O O
        |O O O O O
        |O O O O O
        |O O O O O
        |""".stripMargin.strip()

    assertEquals(game.print(), expected)
  }

  test("should print robot's position") {
    game.execute(PlaceCmd(1, 1, East))

    val expected =
      """
        |O O O O O
        |O → O O O
        |O O O O O
        |O O O O O
        |O O O O O
        |""".stripMargin.strip()

    assertEquals(game.print(), expected)
  }
}
