package tree

import com.sun.source.tree.Tree
import queue.StackQueue
import java.util.Queue

class TreeNode<T: Any>(val value: T) {

    private val children: MutableList<TreeNode<T>> =  mutableListOf()

    fun add(child: TreeNode<T>) = children.add(child)

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = StackQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }
        var node = queue.dequeue()
        while (node != null) {
            visit(this)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }

    fun search(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null
        forEachLevelOrder {
            if (it.value == value) result = it
        }
        return result
    }

    fun printEachLevel() {
        val queue = StackQueue<TreeNode<T>>()
        queue.enqueue(this)
        var nodesLeftInCurrentLevel = 0
        while (queue.isEmpty.not()) {

            nodesLeftInCurrentLevel = queue.count

            while (nodesLeftInCurrentLevel > 0) {

                var node = queue.dequeue()
                node?.let {
                    print(node.value)
                    node.children.forEach{ queue.enqueue(it) }
                    nodesLeftInCurrentLevel--
                } ?: break
            }
            println()
        }
    }
}

typealias Visitor<T> = (TreeNode<T>) -> Unit