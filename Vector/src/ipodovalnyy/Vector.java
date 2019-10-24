package ipodovalnyy;

import java.util.Arrays;

public class Vector {
    private double[] elements;
    private int size;

    Vector(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше нуля");
        }
        this.size = size;
        elements = new double[size];
    }

    Vector(Vector vector) {
        this(vector.size, vector.elements);
    }

    Vector(double[] array) {
        this(array.length, array);
    }

    Vector(int size, double[] array) {
        this.size = size;
        elements = new double[size];
        elements = Arrays.copyOf(array, size);
    }

    public void sum(Vector vector) {
        elements = sum(this, vector).elements;
        size = elements.length;
    }

    public void sub(Vector vector) {
        elements = sub(this, vector).elements;
        size = elements.length;
    }

    public double multiply(Vector vector) {
        return multiply(this, vector);
    }

    public void multiply(double num) {
        for (int i = 0; i < size; i++) {
            elements[i] *= num;
        }
    }

    public void revers() {
        multiply(-1);
    }

    public int getSize() {
        return size;
    }

    public void setElement(int index, double num) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше нуля");
        }
        if (index > size) {
            throw new IllegalArgumentException("Индекс должен быть меньше длины вектора");
        }
        this.elements[index] = num;
    }

    public double getElement(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше нуля");
        }
        if (index > size) {
            throw new IllegalArgumentException("Индекс должен быть меньше длины вектора");
        }
        return this.elements[index];
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        if (vector1.size < vector2.size) {
            vector1 = new Vector(vector2.size, vector1.elements);
        } else {
            vector2 = new Vector(vector1.size, vector2.elements);
        }

        Vector vector = new Vector(vector1.size);
        for (int i = 0; i < vector.size; i++) {
            vector.elements[i] = vector1.elements[i] + vector2.elements[i];
        }
        return vector;
    }

    public static Vector sub(Vector vector1, Vector vector2) {
        if (vector1.size < vector2.size) {
            vector1 = new Vector(vector2.size, vector1.elements);
        } else {
            vector2 = new Vector(vector1.size, vector2.elements);
        }

        Vector vector = new Vector(vector1.size);
        for (int i = 0; i < vector.size; i++) {
            vector.elements[i] = vector1.elements[i] - vector2.elements[i];
        }
        return vector;
    }

    public static double multiply(Vector vector1, Vector vector2) {
        int sizeVector = vector1.size < vector2.size ? vector1.size : vector2.size;
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
        final int PRIME = 31;
        int hash = 1;
        for (double e : elements) {
            hash = hash * PRIME + Double.hashCode(e);
        }
        hash = hash * PRIME + size;
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
        Vector temp = (Vector) obj;
        return this.size == temp.size && Arrays.equals(this.elements, temp.elements);
    }

}