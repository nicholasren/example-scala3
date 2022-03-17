import scala.collection.mutable

case class Node[T](v: T, left: Option[Node[T]], right: Option[Node[T]])

object Node:
  extension[T] (root: Node[T]) //adding behaviour for Node[T]
    def bfs(t: T): Option[List[T]] = {
      var path: List[T] = List()
      val queue: mutable.Queue[Node[T]] = mutable.Queue[Node[T]]()
      queue.enqueue(root)

      while (queue.nonEmpty) {
        val el = queue.dequeue()
        path = el.v :: path
        if (el.v == t) {
          return Some(path.reverse)
        }
        if (el.left.isDefined)
          queue.enqueue(el.left.get)
        if (el.right.isDefined)
          queue.enqueue(el.right.get)
      }
      None
    }


object TreeApp extends App {
  val nil = None

  def s[T](n: Node[T]) = Some(n)

  def node[T](t: T, left: Option[Node[T]], right: Option[Node[T]]) = Node(t, left, right)

  var tree: Node[Int] =
    node(3,
      s(
        node(5,
          s(node(1, nil, nil)),
          s(node(4, nil, nil))
        )
      ),
      s(
        node(2,
          s(node(6, nil, nil)),
          nil
        )
      )
    )

  println(tree.bfs(4))
}