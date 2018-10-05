import CategoryC.CategoryC;

public class Main {
    public static void main(String[] args) {

        CategoryA catA = new CategoryA();
        CategoryB catB = new CategoryB();

        catA.task1();
        catA.task2();
        catA.task3();
        catA.task4();
        catA.task5();

        catB.task1();
        catB.task2Snake();
        catB.task2Spiral();

        CategoryC calc = new CategoryC();
        calc.start();
    }
}
