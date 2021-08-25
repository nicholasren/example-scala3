package ren.nicholas.robot.model

import ren.nicholas.robot.cmd.Cmd
import ren.nicholas.robot.model.{State, Table}

class Robot {
  var state: State = null
  var table: Table = null
  var directions = Array("NORTH", "EAST", "SOUTH", "WEST")

  def execute(cmd: Cmd): Unit = {
    val nextState = cmd.next(state)
    if (table.valid(nextState)) {
      this.state = nextState
    }
  }

  def on(table: Table): Unit = {
    this.table = table
  }
}
