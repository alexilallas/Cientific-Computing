/*
Este arquivo é parte do projeto Scientific Computing
Este é um software livre; você pode redistribuí-lo e/ou
modificá-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 3 da
Licença, ou (na sua opinião) qualquer versão.
Este programa é distribuído na esperança de que possa ser  útil,
mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO
a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
Licença Pública Geral GNU para maiores detalhes.
Você deve ter recebido uma cópia da Licença Pública Geral GNU junto
com este programa, Se não, veja <http://www.gnu.org/licenses/>.
Arquivo: Matrix.java
Descrição: Algumas operações com Matrizes.
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
