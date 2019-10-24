package ipodovalnyy;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Shape getMaxArea(Shape[] shape) {
        Comparator<Shape> comparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape shape0, Shape shape1) {
                double areaShape0 = shape0.getArea();
                double areaShape1 = shape1.getArea();
                if (areaShape0 < areaShape1) {
                    return 1;
                } else if (areaShape0 > areaShape1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        Arrays.sort(shape, comparator);

        return shape[0];
    }

    private static Shape getSecondPerimeter(Shape[] shape) {
        Comparator<Shape> comparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape shape0, Shape shape1) {
                double perimeterShape0 = shape0.getPerimeter();
                double perimeterShape1 = shape1.getPerimeter();
                if (perimeterShape0 < perimeterShape1) {
                    return 1;
                } else if (perimeterShape0 > perimeterShape1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        Arrays.sort(shape, comparator);

        return shape[1];
    }

    public static void main(String[] args) {
        Shape[] shape = new Shape[]{new Square(5), new Square(3), new Square(17),
                new Rectangle(2, 8), new Rectangle(6, 7), new Rectangle(10, 18),
                new Triangle(-10, 10, 20, 20, 3, -30), new Triangle(7, 5, 4, 9, 1, 2),
                new Triangle(1, 2, 3, 8, 4, 1), new Circle(1), new Circle(8), new Circle(12)};

        System.out.println("Max area");
        System.out.println(getMaxArea(shape));

        System.out.println();
        System.out.println("Second perimeter");
        System.out.println(getSecondPerimeter(shape));
    }
}