package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State
import ren.nicholas.robot.model.directions.Direction

case class PlaceCmd(x: Int, y: Int, direction: Direction) extends Cmd {
  def next(current: Option[State]): Option[State] =
    if current.isDefined then
      current
    else
      Some(State(x, y, direction))
}
