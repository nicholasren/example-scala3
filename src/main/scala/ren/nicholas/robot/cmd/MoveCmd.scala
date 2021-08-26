package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State
import ren.nicholas.robot.model.directions.Direction

class MoveCmd extends Cmd {

  def next(current: State): State = {
    if (current != null) {
      val direction: Direction = current.direction
      State(current.x + direction.deltaX, current.y + direction.deltaY, direction)
    } else {
      null
    }
  }
}
