import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class CategoryB {


    public void task1(){
        String str = "([ ] [{ }] )   [ ({}) ]({[]}) {[ ()] }";
        str = str.replaceAll(" ", "");

        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        boolean isValid = true;

        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);

            if (map.keySet().contains(curr)) {
                stack.push(curr);
            } else if (map.values().contains(curr)) {
                if (!stack.empty() && map.get(stack.peek()) == curr) {
                    stack.pop();
                } else {
                    isValid = false;
                    break;
                }
            }
        }

        System.out.println( isValid && stack.empty() ? "SUCCESS" : "FAIL");
    }


    private int[] prepairSourceForTask2(int n){
        Random random = new Random();
        int[] source = new int[n*n];
        for (int i = 0; i < n * n; i ++){
            source[i] = random.nextInt(100);
        }
        Arrays.sort(source);
        return source;
    }

    public void task2Spiral(){
        int n = 5;
        toSpiral(prepairSourceForTask2(n), n);
    }

    public void task2Snake(){
        int n = 5;
        toSnake(prepairSourceForTask2(n), n);
    }

    private void toSnake(int[] source, int n) {
        int[][] matr = new int[n][];
        for (int i = 0; i < n; i++)
        {
            matr[i] = new int[n];
        }

        int k = 0;

        for (int subDiagCount = 0; subDiagCount < 2 * n - 1; subDiagCount++) {
            if (subDiagCount < n) {
                if (subDiagCount % 2 != 0) {
                    for (int i = 0; i <= subDiagCount; i++)
                        matr[i][subDiagCount - i] = source[k++];
                }
                else {
                    for (int i = subDiagCount; i >= 0; i--)
                        matr[i][subDiagCount - i] = source[k++];
                }
            }
            else if (subDiagCount % 2 != 0) {
                for (int i = subDiagCount - n + 1; i < n; i++)
                    matr[i][subDiagCount - i] = source[k++];
            }
            else {
                for (int i = n - 1; i >= subDiagCount - n + 1; i--)
                    matr[i][subDiagCount - i] = source[k++];
            }
        }
        print(matr);
    }

    private void toSpiral(int[] source, int n)
    {
        int[][] matr = new int[n][];
        for (int i = 0; i < n; i++)
        {
            matr[i] = new int[n];
        }

        int currentLength = n;
        int index = 0;
        int offset = 0;

        int size = n * n;

        while (index < size)
        {
            for (int k = offset; k < currentLength & index < size; k++)
                matr[offset][k] = source[index++];
            for (int l = offset + 1; l < currentLength & index < size; l++)
                matr[l][n - offset - 1] = source[index++];
            currentLength = currentLength - 1;
            for (int p = n - offset - 2; p > 0 + offset & index < size; p--)
                matr[n - offset - 1][p] = source[index++];
            for (int h = n - offset - 1; h > 0 + offset & index < size; h--)
                matr[h][offset] = source[index++];
            offset = offset + 1;
        }
        print(matr);
    }

    private void print(int [][] matr)
    {
        for (int i = 0; i < matr.length; i++)
        {
            for (int j = 0; j < matr[0].length; j++)
            {
                System.out.print(matr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}