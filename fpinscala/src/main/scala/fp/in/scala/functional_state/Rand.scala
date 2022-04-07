package fp.in.scala.functional_state

import scala.annotation.tailrec

trait RandGenerator:
  def nextInt: (Int, RandGenerator)

/**
 * a randomly generated '''A'''
 *
 * @tparam A
 */
type Rand[+A] = RandGenerator => (A, RandGenerator)

object Rand:
  def unit[A](a: A): Rand[A] = (rng: RandGenerator) => (a, rng)

  def map[A, B](state: Rand[A])(f: A => B): Rand[B] =
    (rng: RandGenerator) =>
      // generate a new 'a' and a new `RandGenerator`
      val (a, newGenerator) = state(rng)
      // apply 'f' against newly generated 'a' which result to a 'b'
      val b = f(a)
      // return a tuple
      (b, newGenerator)


case class SimpleRandGenerator(seed: Long) extends RandGenerator :
  override def nextInt: (Int, RandGenerator) =
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRandGenerator(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)

object SimpleRandGenerator:

  def nonNegativeInt: Rand[Int] =
    @tailrec
    def go(rng: RandGenerator): (Int, RandGenerator) =
      val next = rng.nextInt
      if (next._1 > 0)
        next
      else
        go(next._2)

    rng => go(rng)

  /**
   * Generate ''count'' number of Int with provided initial ''RNG''
   *
   * @param count number of required ints
   * @return Rand[List[Int]]
   */
  def ints(count: Int): Rand[List[Int]] =
    @tailrec
    def go(result: List[Int])(count: Int)(rng: RandGenerator): (List[Int], RandGenerator) =
      if (count == 0)
        (result, rng)
      else
        val next = rng.nextInt
        go(next._1 :: result)(count - 1)(next._2)

    rng => go(List())(count)(rng)

  def nonNegativeEven: Rand[Int] = Rand.map(nonNegativeInt)(i => i - i % 2)

  def double: Rand[Double] =
    rng =>
      val next = rng.nextInt
      (next._1.toDouble / Int.MaxValue, next._2)

