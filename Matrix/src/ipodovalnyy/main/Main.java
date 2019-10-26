package ipodovalnyy.main;

import ipodovalnyy.matrix.Matrix;
import ipodovalnyy.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix A = new Matrix(5, 5);
        System.out.println("A" + A);
        System.out.println(Arrays.toString(A.getSize()));

        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        Matrix B = new Matrix(array);
        System.out.println("B" + B);

        Vector[] vectors = new Vector[]{new Vector(new double[]{1, 2}), new Vector(new double[]{9, 8, 3}), new Vector(new double[]{7, 6})};
        Matrix C = new Matrix(vectors);
        System.out.println("C" + C);

        Matrix D = new Matrix(B);
        System.out.println("D" + D);

        System.out.println("GetColumn 1" + D.getColumn(1));

        System.out.println("GetString 2" + D.getString(2));

        Vector vector = new Vector(new double[]{3, 1, 2});
        D.setColumn(0, vector);
        System.out.println("SetColumn 0" + D);

        vector = new Vector(new double[]{4, 3, 1, 2});
        D.setString(1, vector);
        System.out.println("SetString 1" + D);

        D.transpose();
        System.out.println("Transpose D" + D);

        vector = new Vector(new double[]{1, 2, 3, 4});
        System.out.println("B multiply Vector" + B.multiply(vector));

        B.multiply(2.0);
        System.out.println("B multiply num" + B);

        array = new double[][]{{11, 12, 13}, {14, 15, 16}, {17, 18, 19}, {21, 22, 23}};
        Matrix E = new Matrix(array);
        System.out.println();
        System.out.println("B" + B + "\n" + "E" + E);
        System.out.println("Static B + E" + Matrix.sum(B, E));
        System.out.println("Static E - B" + Matrix.sub(E, B));

        B.sum(E);
        System.out.println("B + E" + B);

        E.sub(B);
        System.out.println("E - (B + E)" + E);

        E.transpose();
        System.out.println("Static E * B" + Matrix.multiply(E, B));

        System.out.println("Column" + B.getColumn(1));

        System.out.println("String" + B.getString(0));

        array = new double[][]{{1, 2, 3}, {4, 5, 6}, {-7, 8, 9}};
        Matrix H = new Matrix(array);

        System.out.println(H.det());
    }
}
