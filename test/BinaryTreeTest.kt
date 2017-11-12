import org.junit.Test
import org.junit.Assert.*
import java.util.*
import java.util.Arrays

class BinaryTreeTest {
    @Test
    fun add() {
        val tree = KtBinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        assertEquals(3, tree.size)
        assertTrue(tree.contains(5))
        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        assertEquals(6, tree.size)
        assertFalse(tree.contains(8))
        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        assertEquals(9, tree.size)
        assertTrue(tree.contains(8))
        assertTrue(tree.checkInvariant())
        assertEquals(1, tree.first())
        assertEquals(20, tree.last())
    }

    @Test
    fun addKotlin() {
        val tree = KtBinaryTree<Int>()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        assertEquals(3, tree.size)
        assertTrue(tree.contains(5))
        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        assertEquals(6, tree.size)
        assertFalse(tree.contains(8))
        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        assertEquals(9, tree.size)
        assertTrue(tree.contains(8))
        assertTrue(tree.checkInvariant())
        assertEquals(1, tree.first())
        assertEquals(20, tree.last())
    }

    @Test
    fun subSet() {
        val tree = KtBinaryTree<Int>()
        val sortedSet = TreeSet<Int>()
        tree.add(6)
        tree.add(12)
        tree.add(7)
        tree.add(3)
        tree.add(2)
        tree.add(5)
        tree.add(4)
        tree.add(9)
        tree.add(10)
        assertEquals(sortedSet, tree.subSet(6, 6))

        sortedSet.add(5)
        sortedSet.add(6)
        assertEquals(sortedSet, tree.subSet(5, 7))

        sortedSet.add(2)
        sortedSet.add(3)
        sortedSet.add(4)
        sortedSet.add(7)
        sortedSet.add(9)
        sortedSet.add(10)
        assertEquals(sortedSet, tree.subSet(0, 12))

        sortedSet.add(12)
        assertEquals(sortedSet, tree.subSet(-20, 20))

        sortedSet.remove(12)
        assertEquals(sortedSet, tree.subSet(1, 12))
    }

    @Test
    fun remove() {
        var tree = KtBinaryTree<Int>()
        var list = Arrays.asList(1, 2, 0)
        var treeTest = KtBinaryTree<Int>()
        var listTest = Collections.emptyList<Int>()
        treeTest.addAll(listTest)
        assertEquals(treeTest, tree)

        tree = KtBinaryTree<Int>()
        tree.addAll(list)
        tree.remove(1)
        treeTest = KtBinaryTree<Int>()
        listTest = Arrays.asList(2, 0)
        treeTest.addAll(listTest)
        assertEquals(treeTest, tree)

        tree = KtBinaryTree<Int>()
        list = Arrays.asList(1, 2, 3, 4)
        tree.addAll(list)
        tree.remove(1)
        treeTest = KtBinaryTree<Int>()
        listTest = Arrays.asList(2, 3, 4)
        treeTest.addAll(listTest)
        assertEquals(treeTest, tree)

        tree = KtBinaryTree<Int>()
        list = Arrays.asList(6, 12, 7, 3, 2, 5, 4, 9)
        tree.addAll(list)
        tree.remove(3)
        treeTest = KtBinaryTree<Int>()
        listTest = Arrays.asList(6, 12, 7, 4, 2, 5, 9)
        treeTest.addAll(listTest)
        assertEquals(treeTest, tree)

        tree = KtBinaryTree<Int>()
        list = Arrays.asList(1, 2, 3, 4)
        tree.addAll(list)
        tree.remove(2)
        treeTest = KtBinaryTree<Int>()
        listTest = Arrays.asList(1, 3, 4)
        treeTest.addAll(listTest)
        assertEquals(treeTest, tree)

    }
}