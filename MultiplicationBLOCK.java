package MatrixsOperations;
import java.lang.*;
public class MultiplicationBLOCK extends Thread  {
	public static int[][] run(int line, int matrix1[][], int matrix2[][], int matrix3[][], int matrix4[][]){
		int colun = line;
		
		int result [][] = new int[line][colun];
		int result1[][] = new int[line][colun];
		int result2[][] = new int[line][colun];

		int aux = 0;
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < colun; j++) {
				// Blocks Multiplications
				aux = 0;
				for (int k = 0; k < colun; k++) {
					aux += matrix1[i][k] * matrix2[k][j];
					result1[i][j] = aux;
				}
				// Blocks Multiplications
				aux = 0;
				for (int k = 0; k < colun; k++) {
					aux += matrix3[i][k] * matrix4[k][j];
					result2[i][j] = aux;
				}
				// Blocks SUM
				result[i][j] = result1[i][j] + result2[i][j];
			}

		}
		
		return result;
	}
		
}
