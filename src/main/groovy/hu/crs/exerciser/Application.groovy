package hu.crs.exerciser

class Application {

    public static final int MAX_VALUE = 10

    static void main(String[] args) {
        List<Character> operators = ['+', '-'] as List<Character>
        Set<Integer> results = []
        Random random = new Random()

        while (results.size() < 10) {
            def operand0 = random.nextInt(MAX_VALUE * 2)
            def operand1 = random.nextInt(MAX_VALUE)
            def operator = operators[random.nextInt(operators.size())]

            def operand0Padded = operand0.toString().padLeft(3)
            def operatorPadded = operator.toString().padLeft(1)
            def operator1Padded = operand1.toString().padLeft(3)

            def expression = "${operand0Padded} ${operatorPadded} ${operator1Padded}"
            int result = Eval.me(expression) as int
            if (!results.contains(result)) {
                results << result

                println expression
            }
        }

        def oddOneOut = random.nextInt(MAX_VALUE * 3)
        while (results.contains(oddOneOut)) {
            oddOneOut = random.nextInt(MAX_VALUE * 3)
        }

        println("---")
        results.toList().shuffled().forEach { println(it.toString().padLeft(3)) }
        println("---")
        println(oddOneOut.toString().padLeft(3))
    }
}
