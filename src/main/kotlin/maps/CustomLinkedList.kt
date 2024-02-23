package maps

interface Node<T> {
    var next: Node<T>?
}

class RootNode<T> : Node<T> {
    override var next: Node<T>? = null
}

class ValueNode<T>(val value: T) : Node<T> {
    override var next: Node<T>? = null
}

open class CustomLinkedList<T> : MutableIterable<T> {
    private var root: Node<T> = RootNode()

    val isEmpty: Boolean
        get() = root.next == null

    val head: T?
        get() = (root.next as? ValueNode<T>)?.value

    open fun add(value: T) {
        val newNode = ValueNode(value)
        newNode.next = root.next
        root.next = newNode
    }

    fun remove(): T? {
        val headNode = root.next as? ValueNode<T>
        root.next = headNode?.next
        return headNode?.value
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {
            var current: Node<T>? = root
            var nextItem: Node<T>? = root.next
            var lastItem: Node<T>? = null

            override fun hasNext(): Boolean {
                return nextItem != null
            }

            override fun next(): T {
                if (!hasNext()) throw NoSuchElementException()
                lastItem = current
                current = nextItem
                nextItem = current?.next

                return (current as? ValueNode<T>)?.value ?: throw NoSuchElementException()
            }

            override fun remove() {
                if (lastItem == null) throw UnsupportedOperationException()
                lastItem?.next = nextItem
            }
        }
    }
}

fun main() {
    val list = CustomLinkedList<Int>()
    list.add(1)
    list.add(2)
    list.add(3)
    list.forEach { println(it) }
}
