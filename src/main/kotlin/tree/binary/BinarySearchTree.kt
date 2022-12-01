package tree.binary

class BinarySearchTree<T: Comparable<T>>() {

    var root: BinaryNode<T>? = null

    fun isBST(node: BinaryNode<T>? = root): Boolean {
        node ?: return true
        if (node?.leftChild != null) {
            if (node?.leftChild!!.value > node.value) {
                return false
            } else {
                isBST(node?.leftChild)
            }
        }
        if (node?.rightChild != null) {
            if (node?.rightChild!!.value < node.value) {
                return false
            } else {
                isBST(node?.rightChild)
            }
        }
        return true
    }

    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(node: BinaryNode<T>?, value: T): BinaryNode<T>? {
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
        return node
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

    private fun insert(node: BinaryNode<T>?, value: T): BinaryNode<T> {
        node ?: return BinaryNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        return node
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