package ren.nicholas.robot.model

import ren.nicholas.robot.cmd.Cmd
import ren.nicholas.robot.model.{State, Table}

class Robot:
  var state: Option[State] = None
  var table: Table = null

  def on(table: Table) = this.table = table

  def execute(cmd: Cmd) =
    val newState = cmd.next(state)
    if table.valid(newState) then
      this.state = newState
  end execute