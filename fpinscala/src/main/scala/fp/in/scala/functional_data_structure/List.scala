package fp.in.scala.functional_data_structure

import scala.annotation.tailrec

enum List[+A]:
  case Nil
  case Cons(head: A, tail: List[A])

object List:
  def apply[A](as: A*): List[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail *))

  extension[A] (as: List[A])
    def head = as match {
      case Nil => Nil
      case Cons(head, tail) => head
    }

    def tail = as match {
      case Nil => Nil
      case Cons(head, tail) => tail
    }

    def drop(n: Int): List[A] =
      if n <= 0 then as
      else as match {
        case Nil => Nil
        case Cons(_, tail) => tail.drop(n - 1)
      }

    def dropWhile(p: A => Boolean): List[A] = as match {
      case Cons(head, tail) if p(head) => tail.dropWhile(p)
      case _ => as
    }