package fp.in.scala

trait Foldable[F[_]]:
  extension[A] (xs: F[A]) //extension methods for `F[A]`
    def foldWith[B](acc: B)(f: (A, B) => B): B
    def toList: List[A]

object Foldable:
  given Foldable[List] with
    extension[A] (xs: List[A])
      override def foldWith[B](acc: B)(f: (A, B) => B) =
        xs.foldRight(acc)(f)
      override def toList: List[A] = xs