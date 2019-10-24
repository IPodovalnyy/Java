package ipodovalnyy;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private double getMax(double num1, double num2, double num3) {
        double temp = num1 >= num2 ? num1 : num2;
        return temp >= num3 ? temp : num3;
    }

    private double getMin(double num1, double num2, double num3) {
        double temp = num1 < num2 ? num1 : num2;
        return temp < num3 ? temp : num3;
    }

    @Override
    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    @Override
    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));
    }

    private double getLengthSideTriangle(double a1, double b1, double a2, double b2) {
        return Math.sqrt(Math.pow(a2 - a1, 2) + Math.pow(b2 - b1, 2));
    }

    @Override
    public double getPerimeter() {
        double lengthAB = getLengthSideTriangle(x1, y1, x2, y2);
        double lengthBC = getLengthSideTriangle(x2, y2, x3, y3);
        double lengthAC = getLengthSideTriangle(x3, y3, x1, y1);
        return lengthAB + lengthBC + lengthAC;
    }

    @Override
    public String toString() {
        return String.format("Triangle%nx1 = %.1f, y1 = %.1f%nx2 = %.1f, y2 = %.1f%nx3 = %.1f, y3 = %.1f", x1, y1, x2, y2, x3, y3);
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int hash = 1;
        hash = PRIME * hash + Double.hashCode(x1);
        hash = PRIME * hash + Double.hashCode(y1);
        hash = PRIME * hash + Double.hashCode(x2);
        hash = PRIME * hash + Double.hashCode(y2);
        hash = PRIME * hash + Double.hashCode(x3);
        hash = PRIME * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Triangle temp = (Triangle) obj;
        return x1 == temp.x1 && y1 == temp.y1 && x2 == temp.x2 && y2 == temp.y2 && x3 == temp.x3 && y3 == temp.y3;
    }
}