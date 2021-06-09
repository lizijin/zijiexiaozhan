import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val text = launch (Dispatchers.Default) {
        delay(2000)
        println(Thread.currentThread().name)
        "value from ${Thread.currentThread().name}"
    }
    println("ee"+Thread.currentThread().name)

}
