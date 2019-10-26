package ipodovalnyy.matrix;

import ipodovalnyy.vector.Vector;

public class Matrix {
    private Vector[] matrix;
    private int height;
    private int width;

    public Matrix(int height, int width) {
        if (height < 0) {
            throw new IllegalArgumentException("Высота должна быть больше нуля");
        }
        if (width < 0) {
            throw new IllegalArgumentException("Ширина должна быть больше нуля");
        }

        matrix = new Vector[width];
        for (int i = 0; i < width; i++) {
            matrix[i] = new Vector(height);
        }

        this.height = height;
        this.width = width;
    }

    public Matrix(Matrix matrix) {
        this(matrix.matrix);
    }

    public Matrix(double[][] array) {
        height = array[0].length;
        width = array.length;

        int maxVectorLength = 0;
        for (int i = 0; i < width; i++) {
            maxVectorLength = Math.max(maxVectorLength, array[i].length);
        }

        matrix = new Vector[width];
        for (int i = 0; i < width; i++) {
            matrix[i] = new Vector(maxVectorLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        height = vectors[0].getSize();
        width = vectors.length;
        matrix = new Vector[width];

        int maxVectorLength = 0;
        for (int i = 0; i < width; i++) {
            maxVectorLength = Math.max(maxVectorLength, vectors[i].getSize());
        }

        for (int i = 0; i < width; i++) {
            if (vectors[i].getSize() < maxVectorLength) {
                double[] array = new double[maxVectorLength];
                for (int j = 0; j < vectors[i].getSize(); j++) {
                    array[j] = vectors[i].getElement(j);
                }
                vectors[i] = new Vector(array);
            }
            matrix[i] = new Vector(vectors[i]);
        }
    }

    public int[] getSize() {
        return new int[]{height, width};
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше нуля");
        }
        if (index > width) {
            throw new IllegalArgumentException("Индекс должен быть меньше числа столбцов");
        }

        return matrix[index];
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше нуля");
        }
        if (index > width) {
            throw new IllegalArgumentException("Индекс должен быть меньше числа столбцов");
        }
        if (vector.getSize() != height) {
            throw new IllegalArgumentException("Длина вектора должна совпадать с высотой матрицы");
        }

        matrix[index] = new Vector(vector);
    }

    public Vector getString(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше нуля");
        }
        if (index > height) {
            throw new IllegalArgumentException("Индекс должен быть меньше числа строк");
        }

        double[] array = new double[width];
        for (int i = 0; i < width; i++) {
            array[i] = matrix[i].getElement(index);
        }

        return new Vector(array);
    }

    public void setString(int index, Vector vector) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть больше нуля");
        }
        if (index > height) {
            throw new IllegalArgumentException("Индекс должен быть меньше числа строк");
        }
        if (vector.getSize() != width) {
            throw new IllegalArgumentException("Длина вектора должна совпадать с шириной матрицы");
        }

        for (int i = 0; i < width; i++) {
            matrix[i].setElement(index, vector.getElement(i));
        }
    }

    public void transpose() {
        Vector[] matrix = new Vector[height];
        for (int i = 0; i < height; i++) {
            matrix[i] = this.getString(i);
        }

        this.matrix = matrix;
        int temp = height;
        height = width;
        width = temp;
    }

    public void multiply(double num) {
        for (int i = 0; i < width; i++) {
            matrix[i].multiply(num);
        }
    }

    private double[][] getMinor(int index, Vector[] matrix) {
        double[][] array = new double[matrix[0].getSize() - 1][matrix.length - 1];
        int k = 0;
        for (int i = 0; i < height; i++) {
            if (i == index) {
                continue;
            }
            for (int j = 1; j < matrix.length; j++) {
                array[k][j - 1] = matrix[i].getElement(j);
            }
            k++;
        }

        return array;
    }

    public double det() {
        if (height != width) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
        if (height == 1) {
            return matrix[0].getElement(0);
        }
        if (height == 2) {
            return matrix[0].getElement(0) * matrix[1].getElement(1) - matrix[0].getElement(1) * matrix[1].getElement(0);
        }

        double det = 0;
        int sign;

        for (int i = 0; i < height; i++) {
            if (i % 2 == 0) {
                sign = 1;
            } else {
                sign = -1;
            }
            det += sign * matrix[i].getElement(0) * new Matrix(getMinor(i, matrix)).det();
        }

        return det;
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != width) {
            throw new IllegalArgumentException("Длина вектора должна совпадать с шириной матрицы");
        }

        Vector vectorOut = new Vector(matrix.length);
        for (int i = 0; i < height; i++) {
            double temp = 0;
            for (int j = 0; j < width; j++) {
                temp += matrix[j].getElement(i) * vector.getElement(i);
            }
            vectorOut.setElement(i, temp);
        }
        return vectorOut;
    }

    public void sum(Matrix matrix) {
        this.matrix = sum(this, matrix).matrix;
    }

    public void sub(Matrix matrix) {
        this.matrix = sub(this, matrix).matrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.width != matrix2.height) {
            throw new IllegalArgumentException("Матрицы должны быть сопряжены");
        }

        Matrix temp = new Matrix(matrix1.height, matrix2.width);
        for (int i = 0; i < matrix1.height; i++) {
            for (int j = 0; j < matrix1.width; j++) {
                temp.matrix[i].setElement(j, Vector.multiply(matrix1.getString(i), matrix2.getColumn(j)));
            }
        }
        return temp;
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.height != matrix2.height || matrix1.width != matrix2.width) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковую размерность");
        }
        Matrix matrixOut = new Matrix(matrix1.height, matrix1.width);
        for (int i = 0; i < matrix1.width; i++) {
            matrixOut.matrix[i] = Vector.sum(matrix1.matrix[i], matrix2.matrix[i]);
        }

        return matrixOut;
    }

    public static Matrix sub(Matrix matrix1, Matrix matrix2) {
        if (matrix1.height != matrix2.height || matrix1.width != matrix2.width) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковую размерность");
        }
        Matrix matrixOut = new Matrix(matrix1.height, matrix1.width);
        for (int i = 0; i < matrix1.width; i++) {
            matrixOut.matrix[i] = Vector.sub(matrix1.matrix[i], matrix2.matrix[i]);
        }

        return matrixOut;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Vector v : matrix) {
            sb.append(v.toString());
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int hash = 1;
        hash = hash * PRIME + height;
        hash = hash * PRIME + width;
        for (Vector v : matrix) {
            hash = hash * PRIME + v.hashCode();
        }

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

        Matrix temp = (Matrix) obj;
        if (height != temp.height || width != temp.width) {
            return false;
        }

        for (int i = 0; i < width; i++) {
            if (!matrix[i].equals(temp.matrix[i])) {
                return false;
            }
        }
        return true;
    }
}