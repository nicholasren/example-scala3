package ren.nicholas.robot

import ren.nicholas.robot.cmd.PlaceCmd
import ren.nicholas.robot.model.Game
import ren.nicholas.robot.model.Direction.*
import org.scalatest.*
import org.scalatest.funsuite.AnyFunSuite

class GameTest extends AnyFunSuite with BeforeAndAfter {
  var game: Game = null

  before {
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

    assert(game.print() == expected)
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

    assert(game.print() == expected)
  }
}
