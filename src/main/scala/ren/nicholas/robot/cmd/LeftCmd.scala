package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

class LeftCmd extends Cmd {
  def next(current: Option[State]): Option[State] = current.map {
    (state: State) => State(state.x, state.y, state.direction.left)
  }
}
