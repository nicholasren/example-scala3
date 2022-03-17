package fp.in.scala

import munit.FunSuite

class FoldableTest extends FunSuite {

  test("foldable list") {
    val xs = List(1, 2, 3)

    def sum(xs: List[Int])(using Foldable[List]) = xs.foldWith(0)((a, b) => a + b)

    assertEquals(sum(xs), 6)
  }
}
