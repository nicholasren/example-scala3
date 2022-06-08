package fp.in.scala.functional_data_structure

import org.scalatest.funsuite.AnyFunSuite

class ListTest extends AnyFunSuite {
  private val list: List[Int] = List(1, 2, 3)
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

  test("map") {
    assert(list.map(_ * 2) == List(2, 4, 6))
  }
}
