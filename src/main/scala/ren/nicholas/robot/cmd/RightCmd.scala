package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

class RightCmd extends Cmd {
  def next(current: State): State = State(current.x, current.y, current.direction.right)
}
