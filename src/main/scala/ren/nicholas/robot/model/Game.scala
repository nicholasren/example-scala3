package ren.nicholas.robot.model

import ren.nicholas.robot.cmd.Cmd
import ren.nicholas.robot.directions.*

import java.lang.System.lineSeparator

case class Game(width: Int, height: Int) {
  val directionSymbols: Map[Direction, String] = Map(
    North -> "↑",
    East -> "→",
    South -> "↓",
    West -> "←",
  )
  val table = Table(width, height)
  val robot: Robot = Robot()
  {
    robot.on(table)
  }

  def execute(cmd: Cmd) = robot.execute(cmd)

  def print(): String = (0 to width - 1)
    .map {
      row =>
        (0 to height - 1).map {
          column => symbol(row, column)
        }.mkString(" ")
    }.mkString(lineSeparator())

  private def symbol(row: Int, column: Int) = {
    robot.state match {
      case Some(State(x, y, direction)) if x == row && x == column => symbolOf(direction)
      case _ => "O"
    }
  }

  private def symbolOf(direction: Direction): String = directionSymbols.getOrElse(direction, "*")
}
