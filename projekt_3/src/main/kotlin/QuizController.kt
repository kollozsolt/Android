import java.io.File

object QuizController {
    val questions = mutableListOf<Question>()
    val lines = mutableListOf<String>()
    init {
        File("D:\\Egyetem\\VII.felev\\Android\\Android\\projekt_3\\src\\main\\resources\\questions.txt").forEachLine { lines.add(it) }
        for (i in 0..lines.size-1 step 5){
            var text = lines[i]
            var answers = mutableListOf<Answer>()
            for (j in i+1..i+4){
                var index = lines[j].indexOf('/')
                var text = lines[j].substring(0, index)
                var correct  = lines[j].substring(index+1, lines[j].length)
                var answer = Answer(text, correct.toInt())
                answers.add(answer)
            }
            var question = Question(text, answers)
            questions.add(question)
        }
    }

    fun randomizeQuestions(){
        questions.shuffle()
    }

    fun doQuiz(number: Int){
        randomizeQuestions()
        var index = 0
        var rightAnswers: Int = 0
        val options = listOf("a", "b", "c", "d")
        while( index < number) {
            println("${index+1}. ${questions[index].text}")
            questions[index].answers.shuffle()
            for ( i in 0..3 ){
                println("\t${options[i]}. ${questions[index].answers[i].text}")
            }
            var ans = readLine();
            val ind = options.indexOf(ans.toString())
            if (ind != -1 && questions[index].answers[ind].isCorrect == 1){
                rightAnswers ++
            }
            index ++
        }

        println("Helyes valaszok szama: $rightAnswers")
    }
}