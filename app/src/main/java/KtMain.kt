import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.resumeWithException

fun main() = runBlocking<Unit> {

    try {
        suspendCancellableCoroutine {
            thread {
                Thread.sleep(1000)
                try {
                    1 / 0

                } catch (ex: Exception) {
                    it.resumeWithException(ex)
                }

            }
        }
    } catch (e: Exception) {
        println("the exception $e")
//        e.printStackTrace()
    }
}
