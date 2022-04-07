package fp.in.scala.functional_state

import fp.in.scala.functional_state.SimpleRandGenerator.{double, ints, nonNegativeEven, nonNegativeInt}

class RandTest extends munit.FunSuite {

  private val rng: RandGenerator = SimpleRandGenerator(42)

  test("nextInt") {
    assert(16159453 == rng.nextInt._1)
  }

  test("nonNegativeInt") {
    (0 until 100).foreach { _ =>
      assert(nonNegativeInt(rng)._1 > 0)
    }
  }

  test("nonNegativeEven") {
    (0 until 100).foreach { _ =>
      assert(nonNegativeEven(rng)._1 % 2 == 0)
    }
  }

  test("ints") {
    val xs = ints(100)(rng)

    assert(xs._1.length == 100)
  }

  test("double") {
    val d: Double = double(rng)._1

    assert(d > 0 && d < 1)
  }
}
