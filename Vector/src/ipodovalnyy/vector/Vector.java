package ipodovalnyy.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина вектора должена быть больше нуля");
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.elements.length, vector.elements);
    }

    public Vector(double[] array) {
        this(array.length, array);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина вектора должена быть больше нуля");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }

        elements = Arrays.copyOf(array, size);
    }

    public void sum(Vector vector) {
        if (vector.elements.length > this.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            this.elements[i] += vector.elements[i];
        }
    }

    public void sub(Vector vector) {
        if (vector.elements.length > this.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            this.elements[i] -= vector.elements[i];
        }
    }

    public double multiply(Vector vector) {
        return multiply(this, vector);
    }

    public void multiply(double num) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= num;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public int getSize() {
        return elements.length;
    }

    public void setElement(int index, double num) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше нуля");
        }
        if (index > elements.length - 1) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше длины вектора");
        }

        elements[index] = num;
    }

    public double getElement(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше нуля");
        }
        if (index > elements.length - 1) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше длины вектора");
        }

        return elements[index];
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        vector1.sum(vector2);
        return vector1;
    }

    public static Vector sub(Vector vector1, Vector vector2) {
        vector1.sub(vector2);
        return vector1;
    }

    public static double multiply(Vector vector1, Vector vector2) {
        int sizeVector = Math.min(vector1.elements.length, vector2.elements.length);
        double multiply = 0;

        for (int i = 0; i < sizeVector; i++) {
            multiply += vector1.elements[i] * vector2.elements[i];
        }

        return multiply;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (double e : elements) {
            sb.append(e);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vector temp = (Vector) obj;
        return Arrays.equals(elements, temp.elements);
    }
}