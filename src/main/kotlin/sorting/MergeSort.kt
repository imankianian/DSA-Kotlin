package sorting

fun <T:Comparable<T>> List<T>.mergeSort(): List<T> {
    if (this.size < 2) return this
    val middle = this.size / 2
    val left = this.subList(0, middle).mergeSort()
    val right = this.subList(middle, this.size).mergeSort()
    return merge(left, right)
}

private fun <T:Comparable<T>> merge(leftList: List<T>, rightList: List<T>): List<T> {
    var leftIndex = 0
    var rightIndex = 0
    val result = mutableListOf<T>()
    while (leftIndex < leftList.size && rightIndex < rightList.size) {
        if (leftList[leftIndex] < rightList[rightIndex]) {
            result.add(leftList[leftIndex])
            leftIndex++
        } else if (leftList[leftIndex] > rightList[rightIndex]) {
            result.add(rightList[rightIndex])
            rightIndex++
        } else {
            result.add(leftList[leftIndex])
            result.add(rightList[rightIndex])
            leftIndex++
            rightIndex++
        }
    }
    if (leftIndex < leftList.size) result.addAll(leftList.subList(leftIndex, leftList.size))
    if (rightIndex < rightList.size) result.addAll(rightList.subList(rightIndex, rightList.size))
    return result
}

fun <T:Comparable<T>> merge(first: Iterable<T>, second: Iterable<T>): Iterable<T> {
    val result = mutableListOf<T>()
    val firstIterator = first.iterator()
    val secondIterator = second.iterator()
    if (!firstIterator.hasNext()) return second
    if (!secondIterator.hasNext()) return first
    var firstElement = firstIterator.nextOrNull()
    var secondElement = secondIterator.nextOrNull()
    while (firstElement != null && secondElement != null) {
        when {
            firstElement < secondElement -> {
                result.add(firstElement)
                firstElement = firstIterator.nextOrNull()
            }
            secondElement < firstElement -> {
                result.add(secondElement)
                secondElement = secondIterator.nextOrNull()
            }
            else -> {
                result.add(firstElement)
                result.add(secondElement)
                firstElement = firstIterator.nextOrNull()
                secondElement = secondIterator.nextOrNull()
            }
        }
    }
    while (firstElement != null) {
        result.add(firstElement)
        firstElement = firstIterator.nextOrNull()
    }
    while (secondElement != null) {
        result.add(secondElement)
        secondElement = secondIterator.nextOrNull()
    }
    return result
}

private fun <T> Iterator<T>.nextOrNull(): T? {
    return if (this.hasNext()) this.next() else null
}