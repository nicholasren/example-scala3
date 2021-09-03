package ren.nicholas.robot.cmd

import ren.nicholas.robot.directions.Direction
import ren.nicholas.robot.model.State

sealed trait Cmd {
  def next(current: Option[State]): Option[State]
}

case class PlaceCmd(x: Int, y: Int, direction: Direction) extends Cmd {
  def next(current: Option[State]): Option[State] =
    if current.isDefined then
      current
    else
      Some(State(x, y, direction))
}


class LeftCmd extends Cmd {
  def next(current: Option[State]): Option[State] = current.map {
    (state: State) => State(state.x, state.y, state.direction.left)
  }
}

class RightCmd extends Cmd {
  def next(current: Option[State]): Option[State] = current.map {
    state => State(state.x, state.y, state.direction.right)
  }
}

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

