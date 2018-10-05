package CategoryC;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryC {
    private History history;
    private Scanner input;
    private Calculator calculator;

    private static final String menuInfo = "Выберите действие:\n" +
            "1 - Ввести новое выражение;\n" +
            "2 - Просмотреть историю;\n" +
            "3 - Изменить параметры истории;\n" +
            "9 - Справка;\n" +
            "0 - Выход.\n";
    private static final String help = "Справка:\n" +
            "1. Для возведения числа в степень используйте символ ^. Например: 2^2.\n" +
            "2. Для вставки значения из истории используйте функцию \"history(N)\",\n" +
            "\t где N - порядковый номер записи в истории.";


    public CategoryC(){
        input = new Scanner(System.in);
        history = new History();
        history.loadHistory();
        calculator = new Calculator();
    }

    public void start(){
        while (true){
            printInfo();
            String inputStr = input.nextLine();
            if (!inputStr.isEmpty()) {
                int selectedValue;
                try {
                    selectedValue = Integer.valueOf(inputStr);
                } catch (Exception e) {
                    printInputError();
                    printInfo();
                    continue;
                }

                switch (selectedValue) {
                    case 1: {
                        inputExpression();
                        break;
                    }
                    case 2:{
                        historyShow();
                        break;
                    }
                    case 3:{
                        historySetting();
                        break;
                    }
                    case 9:{
                        helpShow();
                        break;
                    }
                    case 0:{
                        history.saveHistory();

                        if (input != null){
                            input.close();
                        }
                        return;
                    }
                    default:{
                        printInputError();
                    }
                }
            }
        }
    }

    private void helpShow() {
        System.out.printf("%s%n%n", help);
    }

    private void historyShow() {
        history.show();
    }

    private void historySetting() {
        System.out.println("Введите число записей (целое, от 5 до 99), сохраняемых в истории:");
        int capacity = new Scanner(System.in).nextInt();

        if (capacity > 99 || capacity < 5){
           printInputError();
        }
        else {
            history.setCapacity(capacity);
        }
    }

    private void inputExpression() {
        System.out.println("Введите выражение:");
        String exp;

        try {
            int i = 5;
            do {
                exp = input.nextLine();
                i--;
            }
            while (exp.isEmpty() && i > 0);
            if (i == 0){
                printInputError();
            }
            else {
                prepareExp(exp);
            }
        }
        catch (Exception e){
            printInputError();
        }
    }

    private void prepareExp(String exp) {
        exp = exp.toLowerCase().replaceAll(" ", "");

        int charIndex = exp.indexOf("history(");
        while (charIndex != -1){
            int endCharIndex = exp.indexOf(")",charIndex);
            int indexInHistory = Integer.parseInt(exp.substring(charIndex + 8, endCharIndex)) - 1;
            String replaceStr = exp.substring(charIndex, endCharIndex + 1);
            exp = exp.replace(replaceStr, history.getHistory(indexInHistory));
            charIndex = exp.indexOf("history(");
        }

        ArrayList<String> strMas = new ArrayList<>();
        String lastStr = "";
        int i = 0;
        while (i < exp.length()){
            if (Character.isDigit(exp.charAt(i))){
                while (i < exp.length() && (Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')){
                    lastStr += exp.charAt(i);
                    i++;
                }
                strMas.add(lastStr);
                lastStr = "";
            }
            else if (Character.isLetter(exp.charAt(i))){
                while (i < exp.length() && Character.isLetter(exp.charAt(i))){
                    lastStr += exp.charAt(i);
                    i++;

                }
                strMas.add(lastStr);
                lastStr = "";
            }
            else {
                strMas.add(String.valueOf(exp.charAt(i)));
                i++;
            }
        }


        String[] exps = strMas.toArray(new String[0]);

        try {
            double result = calculator.solutionExp(exps);
            history.setHistory(exp, result);
            System.out.println(result);
        }
        catch (Exception e) {
            printInputError();
        }
    }


    private void printInputError() {
        System.out.println("Ошибка ввода");
    }

    private void printInfo() {
        System.out.println(menuInfo);
    }
}
