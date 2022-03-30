package fp.in.scala.functional_data_structure

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