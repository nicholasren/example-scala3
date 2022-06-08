package ren.nicholas.robot.model

import ren.nicholas.robot.model.State

case class Table(width: Int, height: Int):
  def valid(state: Option[State]) = state.isDefined
    && state.get.x >= 0 && state.get.x < width
    && state.get.y >= 0 && state.get.y < height
