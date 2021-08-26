package ren.nicholas.robot.model.directions

sealed trait Direction {
  def left: Direction

  def right: Direction

  def deltaX: Int

  def deltaY: Int
}

case object North extends Direction {
  override def right: Direction = East

  override def left: Direction = West

  override def deltaX: Int = 0

  override def deltaY: Int = -1
}

case object East extends Direction {
  override def right: Direction = South

  override def left: Direction = North

  override def deltaX: Int = 1

  override def deltaY: Int = 0
}

case object South extends Direction {
  override def right: Direction = West

  override def left: Direction = East

  override def deltaX: Int = 0

  override def deltaY: Int = 1
}


case object West extends Direction {
  override def right: Direction = North

  override def left: Direction = South

  override def deltaX: Int = -1

  override def deltaY: Int = 0
}
