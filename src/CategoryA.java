import java.util.Arrays;

public class CategoryA {

    public void task1(){
        int[][] triangle = {
                {100,0},
                {0,100},
                {100,200},
        };

        int[] pointD = {40,100};

        int a = (triangle[0][0] - pointD[0]) * (triangle[1][1] - triangle[0][1]) - (triangle[1][0] -
                triangle[0][0]) * (triangle[0][1] - pointD[1]);
        int b = (triangle[1][0] - pointD[0]) * (triangle[2][1] - triangle[1][1]) - (triangle[2][0] -
                triangle[1][0]) * (triangle[1][1] - pointD[1]);
        int c = (triangle[2][0] - pointD[0]) * (triangle[0][1] - triangle[2][1]) - (triangle[0][0] -
                triangle[2][0]) * (triangle[2][1] - pointD[1]);

        if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0))
        {
            System.out.println("IN");
        }
        else
        {
            System.out.println("OUT");
        }
    }

    public void task2() {
        final int matrSize = 3;
        int result = 0;

        int[][] matr = {
                {1,3,8},
                {9,2,1},
                {0,3,7}
        };

        for (int i = 0; i < matrSize; i++) {
            result += (matr[i][i] - matr[matrSize - i - 1][i]);
        }
        System.out.println(result);
    }

    public void task3(){
        int n = 4;

        char[] str = new char[n];
        Arrays.fill(str, ' ');

        for (int i = 0; i < n; i++){
            str[n - i - 1] = '#';
            System.out.println(str);
        }
    }

    public void task4(){
        int[] array = new int[] {1,2,3,4,5,6};
        int k = 5;

        int pairsCount = 0;
        for (int i = 0; i < array.length - 1; i++){
            for (int j = i + 1; j < array.length; j++){
                if((array[i] + array[j]) % k == 0){
                    pairsCount++;
                }
            }
        }
        System.out.println(pairsCount);
    }

    public void task5() {
        int[][] matrix = {
                {1,2,3,4,5,6,7,8,9,0},
                {2,2,2,2,1,2,1,2,3,1},
                {2,7,7,8,1,1,2,1,1,0},
                {6,7,7,8,1,2,3,4,6,8},
        };

        int[][] window = {
                {1,2,3},
                {2,1,1}
        };

        for (int i = 0; i < matrix.length - window.length + 1; i ++){
            for (int j = 0; j < matrix[0].length - window[0].length + 1; j++){
                if (isWindow(matrix, window, i, j)){
                    System.out.printf("(%d,%d)%n", i,j);
                    return;
                }
            }
        }
        System.out.println("FAIL");
    }

    private boolean isWindow(int[][] matr, int[][] window, int indX, int indY){
        for (int i = 0; i < window.length; i++){
            for (int j = 0; j < window[0].length; j++){
                if (matr[i + indX][j + indY] != window[i][j]){
                    return  false;
                }
            }
        }
        return true;
    }
}