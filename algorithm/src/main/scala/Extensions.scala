object Extensions {
  trait Tree[A]

  private case class Leaf[A](value: A) extends Tree[A]

  case class Branch[A](left: Tree[A], value: A, right: Tree[A]) extends Tree[A]

  object Tree:
    extension (tree: Tree[Int])
      def sum: Int = tree match
        case Leaf(value) => value
        case Branch(left, value, right) => left.sum + value + right.sum

    extension[A] (tree: Tree[A])
      def map[B](f: A => B): Tree[B] = tree match
        case Leaf(value) => Leaf(f(value))
        case Branch(left, value, right) => Branch(left.map(f), f(value), right.map(f))

      def forAll(predicate: A => Boolean): Boolean = tree match
        case Leaf(value) => predicate(value)
        case Branch(left, value, right) => left.forAll(predicate) &&
          predicate(value) &&
          right.forAll(predicate)

  def main(args: Array[String]): Unit = {
    val tree = Branch(
      left = Branch(
        left = Leaf(1),
        value = 2,
        right = Leaf(3)
      ),
      value = 4
      ,
      right = Branch(
        left = Leaf(5),
        value = 6,
        right = Leaf(7)
      )
    )

    println(tree)
  }
}