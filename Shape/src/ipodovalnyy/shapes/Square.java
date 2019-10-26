package ipodovalnyy.shapes;

public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return width;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return width * width;
    }

    @Override
    public double getPerimeter() {
        return 4 * width;
    }

    @Override
    public String toString() {
        return String.format("Square%nwidth = %.1f", width);
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        return PRIME + Double.hashCode(width);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Square temp = (Square) obj;
        return width == temp.width;
    }
}