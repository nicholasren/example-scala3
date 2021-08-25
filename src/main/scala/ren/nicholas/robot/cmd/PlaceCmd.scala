package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

case class PlaceCmd(x: Int, y: Int, direction: String) extends Cmd {
  def next(current: State): State = State(x, y, direction)
}
