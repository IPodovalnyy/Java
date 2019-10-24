package ipodovalnyy;

public class Main {
    public static void main(String[] args) {
        // Проверяем работу конструкторов
        Vector A = new Vector(7);
        System.out.println(A);

        Vector B = new Vector(new double[]{1, 2, 3});
        System.out.println(B);

        Vector C = new Vector(B);
        System.out.println(C);

        Vector D = new Vector(7, new double[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(D);

        Vector E = new Vector(5, new double[]{1, 2, 3, 4, 5});
        System.out.println(E);

        // Проверяем нестатические методы для векторов разной длины в разных комбинациях
        E.sum(D);
        System.out.println(E);

        E.sum(B);
        System.out.println(E);

        B.sub(D);
        System.out.println(B);

        E.sub(D);
        System.out.println(E);

        E.multiply(5.5);
        System.out.println(E);

        System.out.println(E.multiply(C));

        E.revers();
        System.out.println(E);

        System.out.println(E.getSize());

        System.out.println(E);
        E.setElement(3, 55);
        System.out.println(E);

        // проверяем отработку проверки корректности входных данных
        try {
            E.setElement(-22, 0.7);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            E.getElement(22);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Проверяем статические методы
        System.out.println(Vector.sum(C, B));

        System.out.println(Vector.sub(B, C));

        System.out.println(Vector.multiply(C, B));
    }
}