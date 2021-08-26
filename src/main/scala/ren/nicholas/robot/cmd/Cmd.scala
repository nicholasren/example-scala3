package ren.nicholas.robot.cmd

import ren.nicholas.robot.model.State

trait Cmd {
  def next(current: Option[State]): Option[State]
}
