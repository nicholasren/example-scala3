package fp.in.scala.functional_data_structure

import fp.in.scala.functional_data_structure.Tree.{Branch, Leaf}
import munit.FunSuite

class TreeTest extends FunSuite {
  private val tree: Tree[Int] =
    Branch(
      Branch(
        Leaf(1),
        3,
        Leaf(2)
      ),
      0,
      Branch(
        Leaf(4),
        6,
        Leaf(7)
      )
    )

  test("size") {
    assert(tree.size == 7)
  }

    test("maximum") {
      assert(tree.maximum == 7)
    }

  test("foldRight") {
    assert(tree.foldRight(0)(_ + _)(_ + _) == 23)
  }
}
