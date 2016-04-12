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
Descrição: Operações com blocos e Threads.
Autor: Alexi Lallas Ribeiro Pereira <alexii2005@hotmail.com>
*/
package MatrixsOperations;
import java.lang.*;

public class Block extends MultiplicationBLOCK{

	public static int [][] Merge(int matrix[][],int line, int caso){

		
		int colun = line;
		int block = line / 2;
		int Block1A [][] = new int[block][block];
		int Block2A [][] = new int[block][block];
		int Block3A [][] = new int[block][block];
		int Block4A [][] = new int[block][block];
		
		int i1, j1, i2, j2, i3, j3, i4, j4;
		i1 = j1 = i2 = j2 = i3 = j3 = i4 = j4 = 0;
		// Block Division
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < colun; j++) {
				switch (caso){
				case 1: 
					if (i < block && j < block) {
						Block1A[i][j] = matrix[i][j];
					}
				case 2:
					if (i < block && j >= block) {
						j2 = j - block;
						if (j2 < 0) {
							j2 = 0;
						}
						Block2A[i][j2] = matrix[i][j];
						j2++;
					}
				case 3:
					if (i >= block && j < block) {
						i3 = i - block;
						if (i3 < 0) {
							i3 = 0;
						}
						Block3A[i3][j] = matrix[i][j];
						i3++;
					}
				case 4:
					if (i >= block && j >= block) {
						i4 = i - block;
						j4 = j - block;
						if (i4 < 0) {
							i4 = 0;
						}
						if (j4 < 0) {
							j4 = 0;
						}
						Block4A[i4][j4] = matrix[i][j];
						j4++;
						i4++;
					}

				}
				
			}
		}
		
		// return only one Block
		if (caso == 1){
			return Block1A;
		}
		if (caso == 2){
			return Block2A;
		}
			
		if (caso == 3){
			return Block3A;
		}
				
		return Block4A;
							
	}
	
	  
	public static int[][] reborn(int line, int result1[][], int result2[][], int result3[][],int result4[][]){
		
			int colun = line;
			int block = line / 2;
			int result[][] = new int [line][colun];
			// joint the blocks
			int i1, j1, i2, j2, i3, j3, i4, j4;
			i1 = j1 = i2 = j2 = i3 = j3 = i4 = j4 = 0;
			for (int i = 0; i < line; i++) {
				for (int j = 0; j < colun; j++) {

					if (i < block && j < block) {
						result[i][j] = result1[i][j];
					}

					if (i < block && j >= block) {
						j2 = j - block;
						if (j2 < 0) {
							j2 = 0;
						}
						result[i][j] = result2[i][j2];
						j2++;
					}

					if (i >= block && j < block) {
						i3 = i - block;
						if (i3 < 0) {
							i3 = 0;
						}
						result[i][j] = result3[i3][j];
						i3++;
					}

					if (i >= block && j >= block) {
						i4 = i - block;
						j4 = j - block;
						if (i4 < 0) {
							i4 = 0;
						}
						if (j4 < 0) {
							j4 = 0;
						}
						result[i][j] = result4[i4][j4];
						j4++;
						i4++;
					}

				}
			}
			return result;
	}
	
	public int[][] MultiplicaBlockThread(int line, int matrix1[][], int matrix2[][]){
		
			int colun = line;
			int block = line / 2;
			
			// merge the matrix to block
			Matrix obj = new Matrix();
			int Block1A [][] = Merge(matrix1,line,1);
			int Block2A [][] = Merge(matrix1,line,2);
			int Block3A [][] = Merge(matrix1,line,3);
			int Block4A [][] = Merge(matrix1,line,4);
			
			int Block1B [][] = Merge(matrix2,line,1);
			int Block2B [][] = Merge(matrix2,line,2);
			int Block3B [][] = Merge(matrix2,line,3);
			int Block4B [][] = Merge(matrix2,line,4);
			
			/* print the blocks
			obj.print(Block1A,block);
			obj.print(Block2A,block);
			obj.print(Block3A,block);
			obj.print(Block4A,block);
			*/
			
			MultiplicationBLOCK thread1 = new MultiplicationBLOCK();
			MultiplicationBLOCK thread2 = new MultiplicationBLOCK();
			MultiplicationBLOCK thread3 = new MultiplicationBLOCK();
			MultiplicationBLOCK thread4 = new MultiplicationBLOCK();
			
			Thread mult1 = new Thread(thread1);
			Thread mult2 = new Thread(thread2);
			Thread mult3 = new Thread(thread3); 
			Thread mult4 = new Thread(thread4);
			
			// Thread Initialization 
			mult1.start();
			mult2.start();
			mult3.start();
			mult4.start();
			
			// Here we'll do the multiplication of the Blocks and the time calculation
			long start = System.currentTimeMillis();
			int result1[][] = thread1.run(block, Block1A, Block1B, Block2A, Block3B);
			int result2[][] = thread2.run(block, Block1A, Block2B, Block2A, Block4B);
			int result3[][] = thread3.run(block, Block3A, Block1B, Block4A, Block3B);
			int result4[][] = thread4.run(block, Block3A, Block2B, Block4A, Block4B);
			long elapsed = System.currentTimeMillis() - start;
			
			// Call reborn() to join the blocks
			int result[][] = reborn(line, result1, result2, result3, result4);
			System.out.println("\nElapsed Time for Multiplication with Blocks in Threads: "+elapsed+" ms\n");
			return result;
	}
	
	public int[][] MultiplicaBlock(int line, int matrix1[][], int matrix2[][]){
		
			int colun = line;
			int block = line / 2;
			
			// merge the matrix to block
			Matrix obj = new Matrix();
			int Block1A [][] = Merge(matrix1,line,1);
			int Block2A [][] = Merge(matrix1,line,2);
			int Block3A [][] = Merge(matrix1,line,3);
			int Block4A [][] = Merge(matrix1,line,4);
			
			int Block1B [][] = Merge(matrix2,line,1);
			int Block2B [][] = Merge(matrix2,line,2);
			int Block3B [][] = Merge(matrix2,line,3);
			int Block4B [][] = Merge(matrix2,line,4);
			
			/* print the blocks
			obj.print(Block1A,block);
			obj.print(Block2A,block);
			obj.print(Block3A,block);
			obj.print(Block4A,block);
			*/

			// Here we'll do the multiplication of the Blocks and the time calculation
			long start = System.currentTimeMillis();
			int result1[][] = run(block, Block1A, Block1B, Block2A, Block3B);
			int result2[][] = run(block, Block1A, Block2B, Block2A, Block4B);
			int result3[][] = run(block, Block3A, Block1B, Block4A, Block3B);
			int result4[][] = run(block, Block3A, Block2B, Block4A, Block4B);
			long elapsed = System.currentTimeMillis() - start;
			
			// Call reborn() to join the blocks
			int result[][] = reborn(line, result1, result2, result3, result4);
			System.out.println("\nElapsed Time for Multiplication with Blocks: "+elapsed+" ms\n");
			return result;
	}
	
}
