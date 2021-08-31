package fp.in.scala

class StreamTest extends munit.FunSuite {
  val list = Stream(1, 2, 3, 4, 5, 6)
  //headOption
  test("headOption") {
    assertEquals(list.headOption, Some(1))
  }

  //to_list_non_tail_recursive
  test("to_list_non_tail_recursive") {
    assertEquals(list.to_list_non_tail_recursive, List(1, 2, 3, 4, 5, 6))
  }

  //toList
  test("toList") {
    assertEquals(list.toList, List(1, 2, 3, 4, 5, 6))
  }

  test("take") {
    assertEquals(list.take(2).toList, List(1, 2))
  }

  test("drop") {
    assertEquals(list.drop(2).toList, List(3, 4, 5, 6))
  }

  test("takeWhile") {
    assertEquals(list.takeWhile(_ < 3).toList, List(1, 2))
  }

  test("foldRight: accumulate from inside out") {
    assertEquals(list.foldRight(0)(_ + _), 21)
  }

  test("forAll") {
    assertEquals(list.forAll(_ > 0), true)
    assertEquals(Stream(-1, 1, 2, -3).forAll(_ > 0), false)
  }

  test("map") {
    assertEquals(list.map(_ + 1).toList, List(2, 3, 4, 5, 6, 7))
  }

  test("filter") {
    assertEquals(list.filter(_ % 2 == 0).toList, List(2, 4, 6))
  }

  test("flatMap") {
    assertEquals(list.flatMap(e => Stream(s"$e", s"$e$e")).toList, List("1", "11", "2", "22", "3", "33", "4", "44", "5", "55", "6", "66"))
  }
}
