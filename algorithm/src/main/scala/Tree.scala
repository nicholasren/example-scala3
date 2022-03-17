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
  var tree: Node[Int] = Node(3,
    Some(
      Node(5,
        Some(Node(1, None, None)),
        Some(Node(4, None, None))
      )
    ),
    Some(
      Node(2,
        Some(Node(6, None, None)),
        None
      )
    )
  )

  println(tree.bfs(4))
}