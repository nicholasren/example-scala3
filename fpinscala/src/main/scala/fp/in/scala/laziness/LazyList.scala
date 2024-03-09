package fp.in.scala.laziness

import fp.in.scala

object LazyList:
  //memorised version of Cons(h, t)
  private def cons[A](head: => A, tail: => LazyList[A]): LazyList[A] = {
    lazy val _head: A = head
    lazy val _tail: LazyList[A] = tail
    Cons(() => _head, () => _tail)
  }

  def empty[A]: LazyList[A] = Empty

  def apply[A](as: A*): LazyList[A] =
    if as.isEmpty then empty
    else cons(as.head, apply(as.tail *))

  /**
   * corecursive function, which produces data.
   *
   * @param start initial state with type `S`
   * @param fun   `S => Option[A, S]` take an state and
   *              optionally produce a new element (with type `A`) and a new state(with type `S`).
   *              The new state will be used as an input for next run
   *              `None` indicates that no more values will be produced.
   * @tparam A type of produced data
   * @tparam S type of initial and intermediate state
   * @return a finite or infinite stream of data
   */
  def unfold[A, S](start: S)(fun: S => Option[(A, S)]): LazyList[A] =
    fun(start) match {
      case Some(a, state) => cons(a, unfold(state)(fun))
      case None => empty
    }

enum LazyList[+A]:
  private case Empty
  case Cons(_head: () => A, _tail: () => LazyList[A])

  import LazyList.*

  /**
   * NOTE: type notation `=> B` indicates that the argument may never get evaluated
   *
   * @param start  initial value of accumulator
   * @param merger merge each value into accumulator
   * @tparam B type of result value
   * @return accumulated value after merging `start` with all elements via `merger`
   */
  def foldRight[B](start: => B)(merger: (A, => B) => B): B = this match
    case Cons(head, tail) => merger(head(), tail().foldRight(start)(merger))
    case _ => start

  def headOption: Option[A] = foldRight(None: Option[A])((element, _) => Some(element))

  def map[B](function: A => B): LazyList[B] = foldRight(empty[B]) {
    (element, acc) => cons(function(element), acc)
  }

  def filter(predicate: A => Boolean): LazyList[A] = foldRight(empty[A]) {
    (el, acc) => if predicate(el) then cons(el, acc) else acc
  }

  private def append[A2 >: A](that: => LazyList[A2]): LazyList[A2] = foldRight(that) {
    (element, acc) => cons(element, acc)
  }

  def flatMap[B](mapper: A => LazyList[B]): LazyList[B] = foldRight(empty[B]) {
    (element, acc) => mapper(element).append(acc)
  }

  /**
   * non tail recursive version of `toList`
   *
   * @return List[A]
   */
  def toList_non_tailrec: List[A] = this match
    case Cons(head, tail) => head() :: tail().toList_non_tailrec
    case Empty => Nil

  def toList: List[A] =
    @annotation.tailrec
    def go(stream: LazyList[A], acc: List[A]): List[A] =
      stream match
        case Cons(head, tail) => go(tail(), head() :: acc)
        case Empty => acc.reverse

    go(this, Nil)


  def take(n: Int): LazyList[A] = this match
    case Cons(head, tail) if n > 1 => cons(head(), tail().take(n - 1))
    case Cons(head, tail) if n == 1 => cons(head(), empty)
    case _ => empty

  def drop(n: Int): LazyList[A] = this match
    case Cons(_, tail) if n > 0 => tail().drop(n - 1)
    case _ => this

  def takeWhile(predicate: A => Boolean): LazyList[A] =
    foldRight(empty[A]) {
      (element, acc) =>
        if predicate(element) then
          cons(element, acc)
        else
          empty
    }

  // `acc` may never get evaluated if `predicate(element)` is false
  def forAll(predicate: A => Boolean): Boolean =
    foldRight(true)((element, acc) => predicate(element) && acc)

  def zipAll[B](that: LazyList[B]): LazyList[(Option[A], Option[B])] =
    unfold(this, that) {
      case (Empty, Empty) => None
      case (Cons(thisHead, thisTail), Empty) =>
        val value = (Some(thisHead()), None)
        val nextState = (thisTail(), Empty)
        Some(value, nextState)
      case (Empty, Cons(thatHead, thatTail)) =>
        val value = (None, Some(thatHead()))
        val nextState = (Empty, thatTail())
        Some(value, nextState)
      case (Cons(thisHead, thisTail), Cons(thatHead, thatTail)) =>
        val value = (Some(thisHead()), Some(thatHead()))
        val nextState = (thisTail(), thatTail())
        Some(value, nextState)
    }

  def tail: LazyList[A] = this match
    case Empty => Empty
    case Cons(head, tail) => tail()

