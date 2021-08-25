package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

class LeftCmd extends Cmd {
  def next(current: State): State = State(current.x, current.y, current.direction.left)
}
