package fp.in.scala.functional_data_structure

import fp.in.scala.functional_data_structure.List.{Cons, Nil}

import scala.annotation.tailrec

enum List[+A]:
  case Nil
  case Cons(h: A, t: List[A])

  def head = this match {
    case Nil => Nil
    case Cons(head, tail) => head
  }

  def tail = this match {
    case Nil => Nil
    case Cons(head, tail) => tail
  }

  def drop(n: Int): List[A] =
    if n <= 0 then this
    else this match {
      case Nil => Nil
      case Cons(_, tail) => tail.drop(n - 1)
    }

  def dropWhile(p: A => Boolean): List[A] = this match {
    case Cons(head, tail) if p(head) => tail.dropWhile(p)
    case _ => this
  }

  /**
   * Convert every element of List[A] to a new type B and retain the same structure (the list)
   * This is an implementation via `foldRight`.
   * Here is another version:
   * {{{
   *  def map[B](f: A => B): List[B] = as match {
   *      case Cons(head, tail) => Cons(f(head), tail.map(f))
   *      case Nil => Nil
   *  }
   * }}}
   *
   * @param f converts A to B
   * @tparam B type of element in result List
   * @return List[B]
   */
  def map[B](f: A => B): List[B] = {
    val init: List[B] = Nil
    val merger: (A, List[B]) => List[B] = (a, acc) => Cons(f(a), acc)
    this.foldRight(init)(merger)
  }

  /**
   * A generic '''folding''' operation on `List[A]` which result into a single value with type `B`.
   * ''right'' means the merging operation only starts and the very end of the list.
   *
   * @param accumulator initial value of accumulator
   * @param merger      merge an instance of `A` into the accumulator(which holds state of currently accumulated values)
   * @tparam B type of folding result
   * @return folding result
   */
  def foldRight[B](accumulator: B)(merger: (A, B) => B): B = this match {
    case Nil => accumulator
    case Cons(head, tail) => merger(head, tail.foldRight(accumulator)(merger))
  }

object List:
  def apply[A](as: A*): List[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail *))