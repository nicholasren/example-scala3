package ren.nicholas.robot

class Robot {
  var state: State = null
  var table: Table = null
  var directions = Array("NORTH", "EAST", "SOUTH", "WEST")

  def execute(cmd: Cmd): Unit = {
    if (cmd.isInstanceOf[PlaceCmd]) {
      var placeCmd = cmd.asInstanceOf[PlaceCmd]
      if (placeCmd.x < table.width && placeCmd.y < table.heigth) {
        this.state = State(placeCmd.x, placeCmd.y, placeCmd.direction)
      }
    } else if (cmd.isInstanceOf[LeftCmd]) {
      this.state = State(state.x, state.y, leftOf(state.direction))
    } else if(cmd.isInstanceOf[RightCmd]) {
      this.state = State(state.x, state.y, rightOf(state.direction))
    }
  }

  def leftOf(direction: String): String = {
    val idx = directions.indexOf(direction)
    directions((idx + 4 - 1) % 4)
  }

  def rightOf(direction: String): String = {
    val idx = directions.indexOf(direction)
    directions((idx + 4 + 1) % 4)
  }

  def on(table: Table): Unit = {
    this.table = table
  }
}
