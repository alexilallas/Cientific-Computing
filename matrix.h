/*
Este arquivo é parte do projeto Data Structures
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
Arquivo: matrix.h
Descrição: Biblioteca de operações matriciais.
Autor: Alexi Lallas Ribeiro Pereira <alexii2005@hotmail.com>
*/

#pragma once
#include<stdlib.h>
#include<stdio.h>
#include<time.h>

int **alloc(int line) {
	int colun = line;
	int **matrix;
	matrix = (int**)malloc(line*sizeof(int*));
	for (int i = 0; i < line; i++) {
		matrix[i] = (int*)malloc(colun*sizeof(int));
	}
	return matrix;
}

int **atribuition(int line, int**matrix) {
	int colun = line;
	for (int i = 0; i < line; i++) {
		for (int j = 0; j < colun; j++) {
			matrix[i][j] = rand() % 10;
		}
	}
	return matrix;
}

int **matrixzero(int line, int**matrix) {
	int colun = line;
	for (int i = 0; i < line; i++) {
		for (int j = 0; j < colun; j++) {
			matrix[i][j] = 0;
		}
	}
	return matrix;
}

void print(int line, int **matrix) {
	int colun = line;
	for (int i = 0; i < line; i++) {
		for (int j = 0; j < colun; j++) {
			if (j == colun - 1)
				printf(" %d \n", matrix[i][j]);
			else
				printf(" %d ", matrix[i][j]);
		}
	}
	system("pause");
}

int **multiplication(int line, int **matrix1, int **matrix2) {
	int colun = line;
	int **result = alloc(line);
	result = matrixzero(line, result);
	int aux = 0;
	for (int i = 0; i < line; i++) {
		for (int j = 0; j < colun; j++) {
			aux = 0;
			for (int k = 0; k < colun; k++) {
				aux += matrix1[i][k] * matrix2[k][j];
				result[i][j] = aux;
			}

		}

	}
	return result;
}

int **multiplicationBLOCK(int line, int **matrix1, int **matrix2, int **matrix3, int **matrix4) {
	int colun = line;
	int **result = alloc(line);
	int **result1 = alloc(line);
	int **result2 = alloc(line);

	result = matrixzero(line, result);
	result1 = matrixzero(line, result1);
	result2 = matrixzero(line, result2);

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

int **merge(int line, int**matrix, int caso) {
	int colun = line;
	int block = line / 2;
	int **Block1A = alloc(block);
	int **Block2A = alloc(block);
	int **Block3A = alloc(block);
	int **Block4A = alloc(block);
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
	switch (caso)
	{
		case 1:
		return Block1A;
		
		case 2:
		return Block2A;
		
		case 3:
		return Block3A; 
		
		case 4:
		return Block4A;
	}
	
}

int **reborn(int line, int **result1, int **result2, int **result3,int **result4) {
	int colun = line;
	int block = line / 2;
	int **result = alloc(line);
	
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

int **buildblock(int line, int **matrix1, int **matrix2) {
	int colun = line;
	int block = line / 2;
	int **Block1A = alloc(block);
	int **Block2A = alloc(block);
	int **Block3A = alloc(block);
	int **Block4A = alloc(block);
	int **Block1B = alloc(block);
	int **Block2B = alloc(block);
	int **Block3B = alloc(block);
	int **Block4B = alloc(block);

	// merge the matrix to block
	Block1A = merge(line, matrix1, 1);
	Block2A = merge(line, matrix1, 2);
	Block3A = merge(line, matrix1, 3);
	Block4A = merge(line, matrix1, 4);

	Block1B = merge(line, matrix2, 1);
	Block2B = merge(line, matrix2, 2);
	Block3B = merge(line, matrix2, 3);
	Block4B = merge(line, matrix2, 4);

	// Memory allocation for results matrixs
	int **result1 = alloc(block);
	int **result2 = alloc(block);
	int **result3 = alloc(block);
	int **result4 = alloc(block);
	int **result = alloc(line);

	// Here we'll do the multiplication of the Blocks and the time calculation
	clock_t t0, tf;
	int time;
	t0 = clock();
	result1 = multiplicationBLOCK(block, Block1A, Block1B, Block2A, Block3B);
	result2 = multiplicationBLOCK(block, Block1A, Block2B, Block2A, Block4B);
	result3 = multiplicationBLOCK(block, Block3A, Block1B, Block4A, Block3B);
	result4 = multiplicationBLOCK(block, Block3A, Block2B, Block4A, Block4B);
	tf = clock();
	time = (tf - t0) / (CLOCKS_PER_SEC / 1000);
	// Call reborn() to join the blocks
	result = reborn(line, result1, result2, result3, result4);
	printf("Elapsed Time: %d ms\n",time);

	return result;
}

