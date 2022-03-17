package fp.in.scala

trait Monoid[A]:
  def combine(a1: A, a2: A): A

  def empty: A

object Monoid:
  def stringMonoid: Monoid[String] = new :
    override def combine(a1: String, a2: String): String = a1 + a2

    override def empty: String = ""

  def listMonoid[A]: Monoid[List[A]] = new :
    override def combine(a1: List[A], a2: List[A]): List[A] = a1 ++ a2

    override def empty: List[A] = List.empty

