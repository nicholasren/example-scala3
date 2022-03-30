package fp.in.scala

import fp.in.scala

object Stream:
  //memorised version of Cons(h, t)
  def cons[A](head: => A, tail: => Stream[A]): Stream[A] = {
    lazy val _head = head
    lazy val _tail = tail
    Cons(() => _head, () => _tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if as.isEmpty then empty
    else cons(as.head, apply(as.tail *))

  /**
   * corecursive function, which produces data.
   *
   * @param start intial state with type `S`
   * @param fun   `S => Option[A, S]` take an state and
   *              optionally produce a new element (with type `A`) and a new state(with type `S`).
   *              The new state will be used as an input for next run
   *              `None` indicates that now more values will be produced.
   * @tparam A type of produced data
   * @tparam S type of inital and intermediate state
   * @return a finite or infinite stream of data
   */
  def unfold[A, S](start: S)(fun: S => Option[(A, S)]): Stream[A] =
    fun(start) match {
      case Some(a, state) => cons(a, unfold(state)(fun))
      case None => empty
    }

enum Stream[+A]:
  case Empty
  case Cons(_head: () => A, _tail: () => Stream[A])

  import Stream.*

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

  def map[B](function: A => B): Stream[B] = foldRight(empty[B]) {
    (element, acc) => cons(function(element), acc)
  }

  def filter(predicate: A => Boolean): Stream[A] = foldRight(empty[A]) {
    (el, acc) => if predicate(el) then cons(el, acc) else acc
  }

  def append[A2 >: A](that: => Stream[A2]): Stream[A2] = foldRight(that) {
    (element, acc) => cons(element, acc)
  }

  def flatMap[B](mapper: A => Stream[B]): Stream[B] = foldRight(empty[B]) {
    (element, acc) => mapper(element).append(acc)
  }

  //non tail recursive
  def to_list_non_tail_recursive: List[A] = this match
    case Cons(head, tail) => head() :: tail().to_list_non_tail_recursive
    case Empty => Nil

  def toList: List[A] =
    @annotation.tailrec
    def go(stream: Stream[A], acc: List[A]): List[A] =
      stream match
        case Cons(head, tail) => go(tail(), head() :: acc)
        case Empty => acc.reverse

    go(this, Nil)


  def take(n: Int): Stream[A] = this match
    case Cons(head, tail) if n > 1 => cons(head(), tail().take(n - 1))
    case Cons(head, tail) if n == 1 => cons(head(), empty)
    case _ => empty

  def drop(n: Int): Stream[A] = this match
    case Cons(_, tail) if n > 0 => tail().drop(n - 1)
    case _ => this

  def takeWhile(predicate: A => Boolean): Stream[A] =
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

  def zipAll[B](that: Stream[B]): Stream[(Option[A], Option[B])] =
    unfold(this, that) {
      case (Empty, Empty) => None
      case (Cons(thisHead, thisTail), Empty) => {
        val value = (Some(thisHead()), None)
        val nextState = (thisTail(), Empty)
        Some(value, nextState)
      }
      case (Empty, Cons(thatHead, thatTail)) => {
        val value = (None, Some(thatHead()))
        val nextState = (Empty, thatTail())
        Some(value, nextState)
      }
      case (Cons(thisHead, thisTail), Cons(thatHead, thatTail)) => {
        val value = (Some(thisHead()), Some(thatHead()))
        val nextState = (thisTail(), thatTail())
        Some(value, nextState)
      }
    }

  def tail: Stream[A] = this match
    case Empty => Empty
    case Cons(head, tail) => tail()

