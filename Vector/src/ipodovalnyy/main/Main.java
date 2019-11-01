package ipodovalnyy.main;

import ipodovalnyy.vector.Vector;

public class Main {
    public static void main(String[] args) {
        // Проверяем работу конструкторов
        Vector A = new Vector(7);
        System.out.println("A " + A);

        Vector B = new Vector(new double[]{1, 2, 3});
        System.out.println("B " + B);

        Vector C = new Vector(B);
        System.out.println("C " + C);

        Vector D = new Vector(7, new double[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("D " + D);

        Vector E = new Vector(5, new double[]{1, 2, 3, 4, 5});
        System.out.println("E " + E);

        // Проверяем нестатические методы для векторов разной длины в разных комбинациях
        E.sum(D);
        System.out.println("E + D " + E);

        E.sum(B);
        System.out.println("E + B " + E);

        B.sub(D);
        System.out.println("B - D " + B);

        E.sub(D);
        System.out.println("E - D " + E);

        E.multiply(5.5);
        System.out.println("E * num " + E);

        System.out.println("E * C " + E.multiply(C));

        E.reverse();
        System.out.println("revers E " + E);

        System.out.println("size E " + E.getSize());

        System.out.println("E " + E);
        E.setElement(3, 55);
        System.out.println("E setElement 3 " + E);

        System.out.println("E getElement 2 " + E.getElement(2));

        // проверяем отработку проверки корректности входных данных
        try {
            E.setElement(-7, 0.7);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            E.getElement(7);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Проверяем статические методы
        System.out.println("Static C + B " + Vector.sum(C, B));

        System.out.println("Static C - B " + Vector.sub(B, C));

        System.out.println("Static C * B " + Vector.multiply(C, B));
    }
}