package ren.nicholas.robot.model

import ren.nicholas.robot.cmd.Cmd
import ren.nicholas.robot.model.{State, Table}

class Robot:
  var state: Option[State] = None
  var table: Table = _

  def on(table: Table): Unit = this.table = table

  def execute(cmd: Cmd): Unit =
    val newState = cmd.next(state)
    if table.valid(newState) then
      this.state = newState