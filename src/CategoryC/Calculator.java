package CategoryC;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    HashMap<String, Integer> prefOperationMap = new HashMap<>();
    HashMap<String, Integer> binaryOperationMap = new HashMap<>();

    public Calculator(){
        prefOperationMap.put("sin", 2);
        prefOperationMap.put("cos", 2);
        prefOperationMap.put("tg", 2);
        prefOperationMap.put("ctg", 2);
        prefOperationMap.put("$", 2);
        prefOperationMap.put("!", 2);

        binaryOperationMap.put("+", 0);
        binaryOperationMap.put("-", 0);
        binaryOperationMap.put("*", 1);
        binaryOperationMap.put("/", 1);
        binaryOperationMap.put("^", 2);
    }

    public double solutionExp(String[] exps) throws Exception {
        boolean isSuccess = true;

        Stack<String> stack = new Stack<>();
        String outputString = "";

        int i = 0;

        while(i < exps.length){
            String curItem = exps[i];

            if (isDigit(curItem)){
                outputString += curItem + ' ';
            }
            else if(!isDigit(curItem) && prefOperationMap.getOrDefault(curItem, -1) != -1){
                stack.push(curItem);
            }
            else if (curItem.equals("(")){
                stack.push(String.valueOf('('));
            }
            else if(curItem.equals(")")){
                while (!stack.peek().equals("(")){
                    if (stack.empty()){
                        throw new Exception();
                    }
                    outputString += stack.pop() + ' ';
                }
                stack.pop();

            }
            else if (binaryOperationMap.getOrDefault(curItem, -1) != -1){
                if (curItem.equals("-") && (i == 0 || !isDigit(exps[i-1]))){
                    stack.push("$");
                }
                else if (curItem.equals("+") && (i == 0 || !isDigit(exps[i-1]))){
                }
                else{
                    int priorityCurFunc = binaryOperationMap.get(curItem);
                    while (!stack.empty() &&
                            (prefOperationMap.getOrDefault(stack.peek(), -1) != -1 ||
                                    binaryOperationMap.getOrDefault(stack.peek(), -1) >= priorityCurFunc)) {
                        outputString += stack.pop() + ' ';

                    }
                    stack.push(curItem);
                }
            }
            i++;
        }

        while(!stack.empty()){
            outputString += stack.pop() + ' ';
        }

        return solution(outputString.split(" "));
    }

    private boolean isDigit(String str){
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException  e){
            return false;
        }
    }


    private double solution(String[] exps) throws Exception {
        double result = 0;
        Stack<String> stack = new Stack<>();
        int i = 0;

        while (i < exps.length){
            if (isDigit(exps[i])){
                stack.push(exps[i]);
            }
            else if(prefOperationMap.getOrDefault(exps[i], -1) != -1){
                double val = Double.parseDouble(stack.pop());

                switch (exps[i]){
                    case "!":{
                        stack.push(String.valueOf(calcFact(val)));
                        break;
                    }
                    case "$":{
                        stack.push(String.valueOf(-val));
                        break;
                    }
                    case "sin":{
                        stack.push(String.valueOf(Math.sin(Math.toRadians(val))));
                        break;
                    }
                    case "cos":{
                        stack.push(String.valueOf(Math.cos(Math.toRadians(val))));
                        break;
                    }
                    case "tg":{
                        stack.push(String.valueOf(Math.tan(Math.toRadians(val))));
                        break;
                    }
                    case "ctg":{
                        stack.push(String.valueOf(1 / Math.tan(Math.toRadians(val))));
                        break;
                    }
                }
            }
            else if (binaryOperationMap.getOrDefault(exps[i], -1) != -1){
                double val1 = Double.parseDouble(stack.pop());
                double val2 = Double.parseDouble(stack.pop());

                switch (exps[i]){
                    case "+":{
                        stack.push(String.valueOf(val1 + val2));
                        break;
                    }
                    case "-":{
                        stack.push(String.valueOf(val2 - val1));
                        break;
                    }
                    case "*":{
                        stack.push(String.valueOf(val2 * val1));
                        break;
                    }
                    case "/":{
                        stack.push(String.valueOf(val2 / val1));
                        break;
                    }
                    case "^":{
                        stack.push(String.valueOf(Math.pow(val2, val1)));
                        break;
                    }
                }
            }
            i++;
        }

        if (stack.size() > 1){
            throw  new Exception();
        }
        return  Double.parseDouble(stack.pop());
    }

    private double calcFact(double val) {
        int input = (int)(val);
        int sign = Integer.signum(input);
        input = Math.abs(input);
        if (input == 0 || input == 11){
            return 1 * sign;
        }
        else {
            int i = input - 1;
            while (i > 1){
                input *= i;
                i--;
            }
            return input * sign;
        }
    }

}
