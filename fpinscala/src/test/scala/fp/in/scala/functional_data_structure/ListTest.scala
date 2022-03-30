package fp.in.scala.functional_data_structure

import munit.FunSuite

class ListTest extends FunSuite {
  val list = List(1, 2, 3)
  test("head") {
    assert(list.head == 1)
  }

  test("tail") {
    assert(list.tail == List(2, 3))
  }
}
