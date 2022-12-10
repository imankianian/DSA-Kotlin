package sorting

fun <T:Comparable<T>> MutableList<T>.selectionSort(showPasses:Boolean = false) {
    if (this.size < 2) return
    var swapped = false
    for (current in 0 until this.lastIndex) {
        var lowest = current
        for (other in (current + 1) until this.size) {
            if (this[other] < this[lowest]) {
                lowest = other
            }
        }
        if (lowest != current) {
            swapAt(lowest, current)
        }
        if (showPasses) println(this)
    }
}