package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

trait Cmd {
  def directions = Array("NORTH", "EAST", "SOUTH", "WEST")

  def next(current: State): State
}
