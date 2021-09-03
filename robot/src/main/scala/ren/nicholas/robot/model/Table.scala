package ren.nicholas.robot.model

import ren.nicholas.robot.model.State

case class Table(width: Int, heigth: Int):
  def valid(state: Option[State]) = state.isDefined && state.get.x < width && state.get.y < heigth
