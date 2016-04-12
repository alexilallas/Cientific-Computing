/*
Este arquivo � parte do projeto Scientific Computing
Este � um software livre; voc� pode redistribu�-lo e/ou
modific�-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 3 da
Licen�a, ou (na sua opini�o) qualquer vers�o.
Este programa � distribu�do na esperan�a de que possa ser  �til,
mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O
a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a
Licen�a P�blica Geral GNU para maiores detalhes.
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto
com este programa, Se n�o, veja <http://www.gnu.org/licenses/>.
Arquivo: MultiplicationBLOCK.java
Descri��o: Multiplica��o Matricial por blocos
Autor: Alexi Lallas Ribeiro Pereira <alexii2005@hotmail.com>
*/
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
