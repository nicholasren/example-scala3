package ren.nicholas.robot.model

import ren.nicholas.robot.model.State

case class Table(width: Int, heigth: Int):
  def valid(state: State) = state.x < width && state.y < heigth
