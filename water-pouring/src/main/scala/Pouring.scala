class Pouring(capacity: Vector[Int]) {

  //States
  private type State = Vector[Int]
  private val initialState = capacity.map(x => 0)

  //Moves
  trait Move {
    def change(state: State): State
  }

  private case class Empty(glass: Int) extends Move {
    def change(state: State): State = state.updated(glass, 0)
  }

  private case class Fill(glass: Int) extends Move {
    def change(state: State): State = state.updated(glass, capacity(glass))
  }

  private case class Pour(from: Int, to: Int) extends Move {
    def change(state: State): State = {
      val amount = state(from).min(capacity(to) - state(to))
      state
        .updated(from, state(from) - amount)
        .updated(to, state(to) + amount)
    }
  }

  //Paths
  class Path(history: List[Move], val endState: State) {
    //   why it is fold right?
    //          change
    //           /   \
    //         move change
    //                / \
    //             move  change
    //             		  /	\
    //                 move  initial state

    def extend(move: Move) = new Path(move :: history, move.change(endState))

    def length: Int = history.length

    override def toString: String = (history.reverse mkString " ") + " -->" + endState
  }

  private val initialPath = new Path(List(), initialState)

  val glasses: Seq[Int] = capacity.indices

  private val moves =
    (for (g <- glasses) yield Empty(g)) ++
      (for (g <- glasses) yield Fill(g)) ++
      (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))


  private def from(paths: Set[Path], explored: Set[State]): LazyList[Set[Path]] = {
    if (paths.isEmpty) {
      LazyList.empty
    } else {
      val more = for {
        path <- paths
        next <- moves.map(path.extend)
        if !(explored contains next.endState)
      } yield next
      from(more, explored ++ more.map(_.endState)).appended(paths)
    }
  }

  private val pathSets = from(Set(initialPath), Set(initialState))

  def solutions(target: Int): LazyList[Path] = {
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState.contains(target)
    } yield path
  }

  def shortestSolution(target: Int): Option[Path] = {
    solutions(target).minByOption(_.length)
  }
}