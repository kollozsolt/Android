package Problem1

import java.io.File
object ListDictionary: IDictionary {
    private val words: ArrayList<String> = ArrayList()

    init {
        File(IDictionary.NAME).forEachLine { words.add(it) }
    }

    override fun add(word: String): Boolean {
        return words.add(word)
    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }

}