package fp.in.scala

import fp.in.scala.Monoid.{listMonoid, stringMonoid}

class MonoidTest extends munit.FunSuite {

  test("stringMonoid") {
    assertEquals(stringMonoid.combine("foo", "bar"), "foobar")
  }

  test("listMonoid") {
    assertEquals(listMonoid[Int].combine(List(1, 3), List(4, 5)), List(1, 3, 4, 5))
  }
}
