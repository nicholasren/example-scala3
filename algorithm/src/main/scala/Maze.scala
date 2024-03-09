import scala.collection.mutable
import scala.collection.mutable.Queue
import scala.util.Random


class MazeSolver(size: Int):
  opaque type Coordinate = (Int, Int)
  opaque type Path = List[Coordinate]
  
  val width: Int = size
  var height: Int = size
  var offsets: Set[Coordinate] = Set((0, -1), (0, 1), (-1, 0), (1, 0))

  val start: Coordinate = (0, 0)
  val stop: Coordinate = (size - 1, size - 1)

  val maze: Array[Array[Int]] = Array.ofDim[Int](size, size)

  {
    val random = new Random

    for (x <- 0 until width; y <- 0 until width) {
      if ((x, y) == start || (x, y) == stop)
        maze(x)(y) = 0
      else
        maze(x)(y) = if (random.nextInt(1000) % 2 == 0) 0 else 1
    }
  }

  def solvable: Boolean = solution.isDefined

  def show(): Unit = println(visibleWith())

  def solve(): Unit = println(visibleWith(solution.get))

  private def visibleWith(path: Path = List(), refresh: Boolean = false): String = {
    val table: Seq[IndexedSeq[String]] =
      for (x <- 0 until width)
        yield
          for (y <- 0 until height)
            yield cellIcon(x, y, path)
    table
      .map(row => row.mkString(" "))
      .mkString("\n")
  }

  private
  def solution: Option[Path] = {
    var solution: Option[Path] = None
    var visited: Set[(Int, Int)] = Set[Coordinate]()
    val queue: mutable.Queue[Path] = mutable.Queue[Path]()

    queue.enqueue(List(start))
    while (queue.nonEmpty && solution.isEmpty) {
      val steps@(x, y) :: _  = queue.dequeue : @unchecked
      for ((dx, dy) <- offsets) {
        val newX: Int = x + dx
        val newY: Int = y + dy
        if (
          inRange(newX, newY) &&
            maze(newX)(newY) == 0 &&
            !visited((newX, newY))
        ) {
          visited = visited + ((newX, newY))
          if ((newX, newY) == stop) {
            solution = Some((newX, newY) :: steps)
          }
          else {
            queue.enqueue((newX, newY) :: steps)
          }
        }
      }
    }
    solution
  }

  private def inRange(newX: Int, newY: Int): Boolean =
    newX >= 0 && newX < width &&
      newY >= 0 && newY < height

  private def cellIcon(x: Int, y: Int, path: Path = List()): String = {
    if (maze(x)(y) == 0)
      if ((x, y) == start)
        "⬇️ "
      else if ((x, y) == stop)
        "🚪 "
      else if (path.contains((x, y)))
        "🟩"
      else
        "💎"
    else
      "🟫"
  }


object MazeApp extends App {
  var size = 10

  LazyList
    .continually {
      new MazeSolver(size)
    }
    .filter(_.solvable)
    .take(2).foreach(
    maze => {
      println("=" * size * 3)
      maze.show()
      println("=" * size * 3)
      maze.solve()
      println("=" * size * 3)
    }
  )
}


