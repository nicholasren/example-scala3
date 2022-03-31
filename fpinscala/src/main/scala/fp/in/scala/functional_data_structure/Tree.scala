package fp.in.scala.functional_data_structure

import fp.in.scala.functional_data_structure.Tree.Leaf

enum Tree[+A]:
  case Leaf(value: A)
  case Branch(left: Tree[A], value: A, right: Tree[A])

  def size: Int = this match {
    case Leaf(_) => 1
    case Branch(left, _, right) => left.size + 1 + right.size
  }

  /**
   * A generic folding operation on Tree[A] which result into a single value with type B.
   *
   * @param accumulator initial value
   * @param adder       `(A, B) => B` add a single value of `A` into accumulated  result `B`
   * @param merger      `(B, B) => B` combine two values of `B` into one
   * @tparam B type of folding result
   * @return folding result
   */
  def foldRight[B](accumulator: B)(adder: (A, B) => B)(merger: (B, B) => B): B = this match {
    case Leaf(v) => adder(v, accumulator)
    case Branch(left, v, right) => adder(
      v,
      merger(
        left.foldRight(accumulator)(adder)(merger),
        right.foldRight(accumulator)(adder)(merger))
    )
  }

object Tree:
  extension (tree: Tree[Int])
    def maximum: Int = {
      val max = (x: Int, y: Int) => x.max(y)
      tree.foldRight(0)(max)(max)
    }
