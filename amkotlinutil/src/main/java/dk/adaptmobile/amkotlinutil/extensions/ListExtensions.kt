package dk.adaptmobile.amkotlinutil.extensions

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun <T> List<T>?.isLast(index: Int): Boolean {
    return index == this?.lastIndex
}

fun <T> List<T>?.sizeOrZero(): Int {
    return this?.size ?: 0
}

fun <T> List<T>?.orEmptyString(string: String): String {
    return if(this?.isEmpty() == true) "" else string
}

fun <E> MutableList<E>.removeElement(element: E): E? {
    val index = indexOfOrNull(element) ?: return null
    return removeAt(index)
}

private fun <E> MutableList<E>.indexOfOrNull(element: E): Int? {
    val indexOf = indexOf(element)
    return if (indexOf != -1) indexOf else null
}