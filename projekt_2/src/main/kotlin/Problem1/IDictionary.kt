package Problem1

interface IDictionary {
    companion object{
        val NAME = "D:\\Egyetem\\VII.felev\\Android\\projekt_2\\src\\main\\resources\\text.txt"
    }
    fun add(word: String): Boolean
    fun find(word: String): Boolean
    fun size(): Int
}