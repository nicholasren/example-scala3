package fp.in.scala.laziness

import org.scalatest.funsuite.AnyFunSuite

class LazyListTest extends AnyFunSuite {
  private val lazyList = LazyList(1, 2, 3, 4, 5, 6)
  //headOption
  test("headOption") {
    assert(lazyList.headOption.contains(1))
  }

  //to_list_non_tail_recursive
  test("to_list_non_tail_recursive") {
    assert(lazyList.toList_non_tailrec == List(1, 2, 3, 4, 5, 6))
  }

  //toList
  test("toList") {
    assert(lazyList.toList == List(1, 2, 3, 4, 5, 6))
  }

  test("take") {
    assert(lazyList.take(2).toList == List(1, 2))
  }

  test("drop") {
    assert(lazyList.drop(2).toList == List(3, 4, 5, 6))
  }

  test("takeWhile") {
    assert(lazyList.takeWhile(_ < 3).toList == List(1, 2))
  }

  test("foldRight: accumulate from inside out") {
    assert(lazyList.foldRight(0)(_ + _) == 21)
  }

  test("forAll") {
    assert(lazyList.forAll(_ > 0))
    assert(LazyList(-1, 1, 2, -3).forAll(_ > 0) == false)
  }

  test("map") {
    assert(lazyList.map(_ + 1).toList == List(2, 3, 4, 5, 6, 7))
  }

  test("filter") {
    assert(lazyList.filter(_ % 2 == 0).toList == List(2, 4, 6))
  }

  test("flatMap") {
    assert(lazyList.flatMap(e => LazyList(s"$e", s"$e$e")).toList == List("1", "11", "2", "22", "3", "33", "4", "44", "5", "55", "6", "66"))
  }

  test("zipAll: should produce pair of elements from both stream") {
    assert(LazyList(1, 2, 3).zipAll(LazyList("a", "b", "c")).toList == List((Some(1), Some("a")), (Some(2), Some("b")), (Some(3), Some("c"))))
    assert(LazyList(1, 2, 3).zipAll(LazyList("a", "b")).toList == List((Some(1), Some("a")), (Some(2), Some("b")), (Some(3), None)))
    assert(LazyList(1, 2).zipAll(LazyList("a", "b", "c")).toList == List((Some(1), Some("a")), (Some(2), Some("b")), (None, Some("c"))))
  }

  test("unfold: fibonacci") {
    def fibs(): LazyList[Int] = LazyList.unfold((0, 1)) {
      state => {
        val value = state._1
        val nextState = (state._2, state._1 + state._2)
        Some(value, nextState)
      }
    }

    assert(fibs().take(7).toList == List(0, 1, 1, 2, 3, 5, 8))
  }
}
