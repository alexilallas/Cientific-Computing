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
Arquivo: MultMatrix.java
Descri��o: Classe Principal
Autor: Alexi Lallas Ribeiro Pereira <alexii2005@hotmail.com>
*/
package MatrixsOperations;

import java.util.Random;
import java.lang.*; 
public class MultMatrix{
	public static void main(String[] args){
		
		int loop = 10;
		// Create a Matrix object
		Matrix obj = new Matrix();
				
		// Generate values to matrix
		int A[][] = obj.fun_gera(loop);
		int B[][] = obj.fun_gera(loop);
		
		// print the matrix A and B
		System.out.println("Matrix A:");
		obj.print(A,loop);
		System.out.println("Matrix B:");
		obj.print(B,loop);
		
		// Multiplication A and B
		long start = System.currentTimeMillis();
		int resultNormal[][] = obj.calcula(A,B,loop);
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("\nElapsed Time for Normal Multiplication: "+elapsed+" ms\n");
		
		// print result
		obj.print(resultNormal, loop);
		
		// Merge the matrix in Blocks and multiplies
		Block obj2 = new Block();
		int resultBlock[][] = obj2.MultiplicaBlock(loop, A, B);
		
		// print result
		obj.print(resultBlock, loop);
				
		// Merge the matrix in Blocks and multiplies with Threads
		int resultBlockThread[][] = obj2.MultiplicaBlockThread(loop, A, B);
		
		// print result
		obj.print(resultBlockThread, loop);
				
	}
	
}
