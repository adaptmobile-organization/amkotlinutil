package dk.adaptmobile.amkotlinutil.util

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import hu.akarnokd.rxjava3.debug.RxJavaAssemblyException
import java.util.*
import java.util.regex.Pattern

class RxJava3Debug {

    companion object {
        private const val NEW_LINE_REGEX = "\\n\\r|\\r\\n|\\n|\\r"
        private val STACK_TRACE_ELEMENT_PATTERN: Pattern = Pattern.compile("^at (.*)\\.(.*)\\((.*):([0-9]+)\\)$")
        val basePackages: Array<String> = arrayOf()

        fun setRootCause(throwable: Throwable, rootCause: Throwable): Throwable {
            val causes: MutableList<Throwable> = listCauses(throwable)
            causes.add(rootCause)
            return reverseAndCollapseCauses(causes)
        }

        private fun listCauses(throwable: Throwable): MutableList<Throwable> {
            val causes: LinkedList<Throwable> = LinkedList()
            var cause = throwable.cause
            while (cause != null && !causes.contains(cause)) {
                causes.add(cause)
                cause = cause.cause
            }
            return causes
        }

        private fun reverseAndCollapseCauses(causes: List<Throwable>): Throwable {
            if (causes.isEmpty()) {
                return RuntimeException("Empty list of causes")
            }
            val topMessage = if (causes[0] is RxJavaAssemblyException) {
                "caused by " + causes[causes.size - 1].localizedMessage
            } else {
                "caused by " + causes[0].javaClass.name + ": " + causes[0].localizedMessage
            }
            var topThrowable: Throwable? = null
            for (i in causes.indices.reversed()) {
                topThrowable = if (i == causes.size - 1) {
                    Throwable(topMessage, topThrowable)
                } else {
                    Throwable(causes[i].message, topThrowable)
                }
                topThrowable.stackTrace = causes[i].stackTrace
            }
            return topThrowable!!
        }

        /**
         * Extract StackTrace and filter to show an app-specific entry at its top
         *
         * @param exception RxJavaAssemblyException to be parsed
         * @return StackTrace, filtered so a app-specific line is at the top of it
         */
        fun parseStackTrace(exception: RxJavaAssemblyException, @Nullable basePackages: Array<String>?): Array<StackTraceElement> {
            val lines: Array<String> = exception.stacktrace()
                    .split(NEW_LINE_REGEX).toTypedArray()
            val stackTrace = mutableListOf<StackTraceElement>()
            var filterIn = false
            for (line in lines) {
                filterIn = (filterIn || basePackages == null || basePackages.isEmpty() || startsWithAny(line, basePackages))
                if (filterIn) {
                    val element = parseStackTraceLine(line)
                    if (element != null) {
                        stackTrace.add(element)
                    }
                }
            }
            return stackTrace.toTypedArray()
        }

        private fun startsWithAny(input: String, matchers: Array<String>): Boolean {
            for (matcher in matchers) {
                if (input.startsWith("at $matcher") || input.startsWith(matcher)) return true
            }
            return false
        }

        /**
         * Parse string containing a *single line* of a StackTrace
         *
         * @param stackTraceLine string containing single line of a StackTrace
         * @return parsed StackTraceElement
         */
        private fun parseStackTraceLine(stackTraceLine: String): StackTraceElement? {
            var retVal: StackTraceElement? = null
            val matcher = STACK_TRACE_ELEMENT_PATTERN.matcher(stackTraceLine)
            if (matcher.matches()) {
                val clazz = matcher.group(1)
                val method = matcher.group(2)
                val filename = matcher.group(3)
                val line = Integer.valueOf(matcher.group(4))
                retVal = StackTraceElement(clazz, method, filename, line)
            }
            return retVal
        }
    }
}
/**
 * Obtain a copy of the original Throwable with an extended StackTrace
 *
 * @param original Original Throwable
 * @return new Throwable with enhanced StackTrace if information was found. *Original Throwable* otherwise
 */
@NonNull
fun Throwable.getEnhancedStackTrace(): Throwable {
    val assembledException = RxJavaAssemblyException.find(this)
    if (assembledException != null) {
        val clearStack = RxJava3Debug.parseStackTrace(assembledException, RxJava3Debug.basePackages)
        val enhancedMessage = this.toString()
        val clearException = Throwable(enhancedMessage)
        clearException.stackTrace = clearStack
        return RxJava3Debug.setRootCause(this, clearException)
    }
    return this
}