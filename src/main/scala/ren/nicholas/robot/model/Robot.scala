package ren.nicholas.robot.model

import ren.nicholas.robot.cmd.Cmd
import ren.nicholas.robot.model.{State, Table}

class Robot:
  var state: State = null
  var table: Table = null

  def on(table: Table) = this.table = table

  def execute(cmd: Cmd) =
    val nextState = cmd.next(state)
    if table.valid(nextState) then
      this.state = nextState
  end execute