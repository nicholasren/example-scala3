package ren.nicholas.robot.model.directions

sealed trait Direction {
  def left: Direction
  def right: Direction
}

case object North extends Direction {
  override def right: Direction = East
  override def left: Direction = West
}

case object East extends Direction {
  override def right: Direction = South

  override def left: Direction = North
}

case object South extends Direction {
  override def right: Direction = West

  override def left: Direction = East
}


case object West extends Direction {
  override def right: Direction = North

  override def left: Direction = South
}
