package fp.in.scala

import fp.in.scala.Monoid.{listMonoid, stringMonoid}
import org.scalatest.funsuite.AnyFunSuite

class MonoidTest extends AnyFunSuite {

  test("stringMonoid") {
    assert(stringMonoid.combine("foo", "bar") == "foobar")
  }

  test("listMonoid") {
    assert(listMonoid[Int].combine(List(1, 3), List(4, 5)) == List(1, 3, 4, 5))
  }
}
