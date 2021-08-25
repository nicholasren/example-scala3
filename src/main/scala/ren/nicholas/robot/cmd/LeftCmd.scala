package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

class LeftCmd extends Cmd {
  def next(current: State): State = State(current.x, current.y, leftOf(current.direction))

  def leftOf(direction: String): String = {
    val idx = directions.indexOf(direction)
    directions((idx + 4 - 1) % 4)
  }
}
