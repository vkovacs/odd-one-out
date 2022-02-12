package hu.crs.exerciser

import groovy.transform.ToString

class Application {

    public static final int MAX_VALUE = 10
    private static final List<Character> operators = ['+', '-'] as List<Character>
    private static final Random random = new Random()

    static void main(String[] args) {

        5.times {
            println exercise()
        }
    }

    private static Exercise exercise() {
        Set<String> expressions = []

        Set<Integer> results = []
        while (results.size() < 10) {
            def operand0 = random.nextInt(MAX_VALUE * 2)
            def operand1 = random.nextInt(MAX_VALUE)
            def operator = operators[random.nextInt(operators.size())]
            def expression = "${operand0} ${operator} ${operand1}".toString()
            expressions << expression

            int result = Eval.me(expression) as int
            if (!results.contains(result)) {
                results << result
            }
        }

        def oddOneOut = random.nextInt(MAX_VALUE * 3)
        while (results.contains(oddOneOut)) {
            oddOneOut = random.nextInt(MAX_VALUE * 3)
        }

        def possibleResults = results.toList().shuffled().collect()

        return new Exercise(expressions: expressions, possibleResults: possibleResults)
   }

    @ToString
    private static class Exercise {
        Set<String> expressions
        Set<Integer> possibleResults
    }
}
