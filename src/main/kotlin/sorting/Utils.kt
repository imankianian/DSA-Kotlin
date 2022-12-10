package sorting

fun <T> MutableList<T>.rev() {
    var left = 0
    var right = this.lastIndex
    while (left < right) {
        swapAt(left, right)
        left++
        right--
    }
}

fun <T:Comparable<T>> MutableList<T>.biggestDuplicate(): T? {
    this.selectionSort()
    for (i in this.lastIndex downTo 1) {
        if (this[i] == this[i - 1]) return this[i]
    }
    return null
}

fun <T:Comparator<T>> MutableList<T>.rightAlign(element: T) {
    if (this.size < 2) return
    var searchIndex = this.size - 2
    while (searchIndex >= 0) {
        if (this[searchIndex] == element) {
            var moveIndex = searchIndex
            while (moveIndex < this.size - 1 && this[moveIndex + 1] != element) {
                swapAt(moveIndex, moveIndex + 1)
                moveIndex++
            }
        }
        searchIndex--
    }
}

fun <T> MutableList<T>.swapAt(first: Int, second: Int) {
    val temp = this[first]
    this[first] = this[second]
    this[second] = temp
}