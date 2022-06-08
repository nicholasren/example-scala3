package ren.nicholas.robot.directions

import ren.nicholas.robot.directions.Direction.North

enum Direction:
  case North, East, South, West

  def left: Direction = Direction.fromOrdinal((this.ordinal + 3) % 4)

  def right: Direction = Direction.fromOrdinal((this.ordinal + 1) % 4)

  def deltaX: Int = this match
    case North | South => 0
    case East => 1
    case West => -1

  def deltaY: Int = this match
    case West | East => 0
    case North => -1
    case South => 1