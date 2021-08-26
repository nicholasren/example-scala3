package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State
import ren.nicholas.robot.model.directions.Direction

class MoveCmd extends Cmd {

  def next(current: Option[State]): Option[State] = current.map {
    state => {
      val direction: Direction = state.direction
      val newX = state.x + direction.deltaX
      val newY = state.y + direction.deltaY
      State(newX, newY, direction)
    }
  }
}
