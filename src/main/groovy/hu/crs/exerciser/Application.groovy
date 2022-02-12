package hu.crs.exerciser

import groovy.transform.ToString

class Application {

    public static final int MAX_VALUE = 10
    private static final List<Character> operators = ['+', '-'] as List<Character>
    private static final Random random = new Random()

    static void main(String[] args) {

        List<Exercise> exercises = []
        10.times {
            exercises << exercise()
        }

        exportToCsv(new File("build/tmp/exercises.csv"), exercises)
    }

    private static Exercise exercise() {
        Set<String> expressions = []

        Set<Integer> results = []
        while (results.size() < 10) {
            def operand0 = random.nextInt(MAX_VALUE * 2)
            def operand1 = random.nextInt(MAX_VALUE)
            def operator = operators[random.nextInt(operators.size())]
            def expression = "${operand0} ${operator} ${operand1}".toString()
            int result = Eval.me(expression) as int
            if (!results.contains(result)) {
                expressions << expression
                results << result
            }
        }

        def oddOneOut = random.nextInt(MAX_VALUE * 3)
        while (results.contains(oddOneOut)) {
            oddOneOut = random.nextInt(MAX_VALUE * 3)
        }

        def possibleResults = [] << oddOneOut
        possibleResults.addAll(results.toList())
        possibleResults = possibleResults.shuffled().collect()

        return new Exercise(expressions: expressions, possibleResults: possibleResults)
    }

    static void exportToCsv(File file, List<Exercise> exercises) {
        file.write("")
        String line = ""
        exercises[0].expressions.size().times { i ->
            line = exercises
                    .collect { exercise ->
                        exercise.expressions[i]
                    }
                    .join(",,")
            file.append(line + "\n")
        }

        file.append("\n")

        exercises[0].possibleResults.size().times { i ->
            line = exercises
                    .collect { exercise ->
                        exercise.possibleResults[i]
                    }
                    .join(",,")
            file.append(line + "\n")
        }
    }

    @ToString
    private static class Exercise {
        Set<String> expressions
        Set<Integer> possibleResults
    }
}
