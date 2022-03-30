package fp.in.scala.functional_data_structure

import munit.FunSuite

class ListTest extends FunSuite {
  private val list = List(1, 2, 3)
  test("head") {
    assert(list.head == 1)
  }

  test("tail") {
    assert(list.tail == List(2, 3))
  }

  test("drop") {
    assert(list.drop(2) == List(3))
    assert(list.drop(1) == List(2, 3))
  }

  test("dropWhile") {
    assert(list.dropWhile(_ % 2 != 0) == List(2, 3))
    assert(list.dropWhile(_ <= 2) == List(3))
  }
}
