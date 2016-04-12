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
Arquivo: MultMatrix.java
Descrição: Classe Principal
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
