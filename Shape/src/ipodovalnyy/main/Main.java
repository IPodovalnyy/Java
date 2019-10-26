package ipodovalnyy.main;

import ipodovalnyy.shapes.*;

import java.util.Arrays;

public class Main {
    private static Shape getShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new ComparatorArea());
        return shapes[0];
    }

    private static Shape getShapeWithSecondPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new ComparatorPerimeter());
        return shapes[1];
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{new Square(5), new Square(3), new Square(17),
                new Rectangle(2, 8), new Rectangle(6, 7), new Rectangle(10, 18),
                new Triangle(-10, 10, 20, 20, 3, -30), new Triangle(7, 5, 4, 9, 1, 2),
                new Triangle(1, 2, 3, 8, 4, 1), new Circle(1), new Circle(8), new Circle(12)};

        System.out.println("Max area");
        System.out.println(getShapeWithMaxArea(shapes));

        System.out.println();
        System.out.println("Second perimeter");
        System.out.println(getShapeWithSecondPerimeter(shapes));
    }
}