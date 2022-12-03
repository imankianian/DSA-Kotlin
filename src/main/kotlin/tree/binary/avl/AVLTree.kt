package tree.binary.avl

import tree.binary.BinarySearchTree
import kotlin.math.max
import kotlin.math.pow

class AVLTree<T: Comparable<T>>() {

    var root: AVLNode<T>? = null

    fun leafNodes(height: Int): Int {
        return 2.0.pow(height).toInt()
    }

    fun nodes(height: Int): Int {
        return 2.0.pow(height + 1).toInt() - 1
    }

    private fun balanced(node: AVLNode<T>): AVLNode<T> {
        return when (node.balanceFactor) {
            2 -> {
                if (node.leftChild?.balanceFactor == -1) {
                    leftRightRotate(node)
                } else {
                    rightRotate(node)
                }
            }
            -2 -> {
                if (node.rightChild?.balanceFactor == 1) {
                    rightLeftRotate(node)
                } else {
                    leftRotate(node)
                }
            }
            else -> node
        }
    }

    private fun rightLeftRotate(node: AVLNode<T>): AVLNode<T> {
        val rightChild = node.rightChild ?: return node
        node.rightChild = rightRotate(rightChild)
        return leftRotate(node)
    }

    private fun leftRightRotate(node: AVLNode<T>): AVLNode<T> {
        val leftChild = node.leftChild ?: return node
        node.leftChild = leftRotate(leftChild)
        return rightRotate(node)
    }

    private fun leftRotate(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.rightChild!!
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun rightRotate(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.leftChild!!
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(node: AVLNode<T>?, value: T): AVLNode<T>? {
        node ?: return null
        when {
            (value == node.value) -> {
                if (node.leftChild == null && node.rightChild == null) return null
                if (node.leftChild == null) return node.rightChild
                if (node.rightChild == null) return node.leftChild
                node.rightChild?.min?.value?.let {
                    node.value = it
                }
                node.rightChild = remove(node.rightChild, node.value)
            }
            (value < node.value) -> node.leftChild = remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        val balancedNode = balanced(node)
        balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
        return balancedNode
    }

    fun contain(value: T): Boolean {
        var node = root
        while (node != null) {
            if (node!!.value == value) return true
            node = if (value < node!!.value) {
                node.leftChild
            } else {
                node.rightChild
            }
        }
        return false
    }

    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun insert(node: AVLNode<T>?, value: T): AVLNode<T> {
        node ?: return AVLNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        val balancedNode = balanced(node)
        balancedNode.height = max(balancedNode.leftHeight,
            balancedNode.rightHeight) + 1
        return balancedNode
    }

    fun contains(subtree: BinarySearchTree<T>): Boolean {
        val set = mutableSetOf<T?>()
        this.root?.traverseInOrder {
            set.add(it)
        }
        var isEqual = true
        subtree.root?.traverseInOrder {
            if (!set.contains(it)) {
                isEqual = false
                return@traverseInOrder
            }
        }
        return isEqual
    }

    override fun equals(other: Any?): Boolean {
        return (this.root?.value == (other as BinarySearchTree<*>)?.root?.value
                && this.root?.leftChild == other?.root?.leftChild
                && this.root?.rightChild == other?.root?.rightChild)
    }

    override fun toString() = root?.toString() ?: "Empty Tree"
}