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
Arquivo: Matrix.java
Descri��o: Algumas opera��es com Matrizes.
Autor: Alexi Lallas Ribeiro Pereira <alexii2005@hotmail.com>
*/
package MatrixsOperations;

import java.util.Random;

public class Matrix {

	public int[][] fun_gera(int loop){
		
		int matrix[][] = new int [loop][loop];
				
		Random rnd = new Random();
		
		for(int i=0;i<loop;i++){
			for(int j=0;j<loop;j++){
				matrix[i][j] = rnd.nextInt(10);
			}
		}
		
		return matrix;
	}
	
	public void print(int matrix[][], int loop){
		
		for(int i=0;i<loop;i++){
			for(int j=0;j<loop;j++){
				if(j==loop-1){
					System.out.print(" "+matrix[i][j]+"\n");
				}else{
					System.out.print(" "+matrix[i][j]);	
				}
				
				
			}
		}
	}
	
	public int[][] calcula(int A[][],int B[][],int loop){
		int result[][] = new int[loop][loop];
		
		for(int i=0;i<loop;i++){
			for(int j=0;j<loop;j++){
				int aux=0;
				for(int k=0;k<loop;k++){
					aux = aux+ A[i][k]*B[k][j];
				}
				result[i][j] = aux;
			}
		}
				
		return result;
	}
}
