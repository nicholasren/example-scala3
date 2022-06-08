package fp.in.scala

import org.scalatest.funsuite.AnyFunSuite

class FoldableTest extends AnyFunSuite {

  test("foldable list") {
    val xs = List(1, 2, 3)

    def sum(xs: List[Int])(using Foldable[List]) = xs.foldWith(0)((a, b) => a + b)

    assert(sum(xs) ==  6)
  }
}
