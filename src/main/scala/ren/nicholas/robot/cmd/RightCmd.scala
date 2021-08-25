package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

class RightCmd extends Cmd {
  def next(current: State): State = State(current.x, current.y, rightOf(current.direction))

  def rightOf(direction: String): String = {
    val idx = directions.indexOf(direction)
    directions((idx + 4 + 1) % 4)
  }
}
