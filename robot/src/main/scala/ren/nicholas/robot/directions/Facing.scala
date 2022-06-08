package ren.nicholas.robot.directions

enum Facing:
  case N, E, S, W

  def left: Facing = Facing.fromOrdinal((this.ordinal + 3) % 4)

  def right: Facing = Facing.fromOrdinal((this.ordinal + 1) % 4)