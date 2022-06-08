package ren.nicholas.robot.directions

enum Facing:
  case N, E, S, W

  def left: Facing = Facing.fromOrdinal((this.ordinal + 3) % 4)

  def right: Facing = Facing.fromOrdinal((this.ordinal + 1) % 4)

  def deltaX: Int = this match
    case N | S => 0
    case E => 1
    case W => -1

  def deltaY: Int = this match
    case W | E => 0
    case N => -1
    case S => 1