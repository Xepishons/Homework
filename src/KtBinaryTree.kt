import java.util.SortedSet
import kotlin.NoSuchElementException
import java.util.TreeSet

class KtBinaryTree<T : Comparable<T>> : AbstractMutableSet<T>(), SortedSet<T> {

    private var root: Node<T>? = null

    override var size = 0
        private set

    class Node<T>(var value: T) {

        var left: Node<T>? = null

        var right: Node<T>? = null
    }

    override fun add(element: T): Boolean {
        val closest = find(element)
        val comparison = if (closest == null) -1 else element.compareTo(closest.value)
        if (comparison == 0) {
            return false
        }
        val newNode = Node(element)
        when {
            closest == null -> root = newNode
            comparison < 0 -> {
                assert(closest.left == null)
                closest.left = newNode
            }
            else -> {
                assert(closest.right == null)
                closest.right = newNode
            }
        }
        size++
        return true
    }

    internal fun checkInvariant(): Boolean =
            root?.let { checkInvariant(it) } ?: true

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

    private fun findParent(value: T): Node<T>? {
        if (root == null || (root as Node<T>).value === value) return null
        return findParent(value, root!!)
    }

    private fun findParent(value: T, start: Node<T>): Node<T> {
        if (start.right != null && start.right!!.value === value || start.left != null && start.left!!.value === value)
            return start
        val comparison = value.compareTo(start.value)
        if (comparison == 0) {
            return start
        } else if (comparison < 0) {
            if (start.left == null) return start
            return findParent(value, start.left!!)
        } else {
            if (start.right == null) return start
            return findParent(value, start.right!!)
        }
    }

    private fun delete(node: Node<T>) {
        put(node, null)
    }

    private fun put(node: Node<T>, newNode: Node<T>?) {
        val parent = findParent(node.value)
        if (parent == null) {
            if (newNode == null) {
                root = null
                return
            }
            root!!.value = newNode.value
            root!!.right = newNode.right
            root!!.left = newNode.left
            return
        }
        if (parent.right === node) parent.right = newNode
        if (parent.left === node) parent.left = newNode
    }

    override fun remove(element: T): Boolean {
        val elem = find(element)

        if (elem == null || elem.value !== element) {
            return false
        }
        if (elem.left == null && elem.right == null) {
            delete(elem)
        } else if (elem.left == null) {
            put(elem, elem.right)
        } else if (elem.right == null) {
            put(elem, elem.left)
        } else {
            val temp = findMin(elem.right)
            val value = temp!!.value
            remove(temp.value)
            size++
            elem.value = value
        }
        size--
        return true
    }

    private fun findMin(root: Node<T>?): Node<T>? {
        if (root == null) {
            return null
        }
        if (root.left != null) {
            return findMin(root.left)
        }
        return root
    }

    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    private fun find(value: T): Node<T>? =
            root?.let { find(it, value) }

    private fun find(start: Node<T>, value: T): Node<T> {
        val comparison = value.compareTo(start.value)
        return when {
            comparison == 0 -> start
            comparison < 0 -> start.left?.let { find(it, value) } ?: start
            else -> start.right?.let { find(it, value) } ?: start
        }
    }

    inner class BinaryTreeIterator : MutableIterator<T> {

        private var current: Node<T>? = null

        private fun findNext(): Node<T>? {
            return null
        }

        override fun hasNext(): Boolean = findNext() != null

        override fun next(): T {
            current = findNext()
            return (current ?: throw NoSuchElementException()).value
        }

        override fun remove() {
            throw UnsupportedOperationException()
        }
    }

    override fun iterator(): MutableIterator<T> = BinaryTreeIterator()

    override fun comparator(): Comparator<in T>? = null

    @Throws(IllegalArgumentException::class)
    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        return TreeSet(subSet(root, first(), last(), TreeSet<T>())).subSet(fromElement, toElement)
    }

    fun subSet(root: Node<T>?, begin: T, end: T, sortedSet: SortedSet<T>): SortedSet<T> {
        if (root == null) return sortedSet
        val begin1 = root.value.compareTo(begin)
        val end2 = end.compareTo(root.value)
        if (begin1 >= 0 && end2 >= 0) {
            sortedSet.add(root.value)
            subSet(root.left, begin, end, sortedSet)
            subSet(root.right, begin, end, sortedSet)
        } else if (begin1 > 0) {
            subSet(root.left, begin, end, sortedSet)
        } else {
            subSet(root.right, begin, end, sortedSet)
        }
        return sortedSet
    }

    override fun headSet(toElement: T): SortedSet<T> {
        throw UnsupportedOperationException()
    }

    override fun tailSet(fromElement: T): SortedSet<T> {
        throw UnsupportedOperationException()
    }

    override fun first(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    override fun last(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }
}
