/*
Este arquivo � parte do projeto Data Structures
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
Arquivo: Matrix.c
Descri��o: Opera��es de multiplica��es matriciais para medida de desempenho.
Autor: Alexi Lallas Ribeiro Pereira <alexii2005@hotmail.com>
*/


#include "math.h"
#include <stdlib.h>
#include <stdio.h>
#include "matrix.h"
#include <time.h>

int main(void) {
	
	start:
	printf("What the dimension of matrix square ?\n");
	int dimension;
	scanf_s("%d", &dimension);

	// Work only with even number
	if (dimension < 2 || dimension > 2000 || dimension%2 != 0) {
	system("cls");
	goto start;
	}
	int line  = dimension;
	int colun = dimension;
	int **A;
	int **B;
	int **C;
	int **D;
	clock_t t0, tf;
	float time;

	// Memory Allocation for matrix
	A = alloc(line);
	B = alloc(line);
	C = alloc(line);
	D = alloc(line);

	// values atribuition
	A = atribuition(line,A);
	B = atribuition(line,B);
	C = matrixzero(line, C);
	D = matrixzero(line, D);
	
	// print matrix A and B
	printf("MATRIX A:\n");
	print(line, A);
	printf("MATRIX B:\n");
	print(line, B);
	
	// A and B Normal multiplication
	t0 = clock();
	C = multiplication(line, A, B);
	tf = clock();

	// A and B Blocks multiplication 
	printf("\n\nBY BLOCKS:\n");
	D = buildblock(line, A, B);
	print(line, D);
	printf("\n");

	// print results and Elapsed Time
	printf("\nNORMAL:");
	printf("\nElapsed Time Normal Multiplication: %d ms\n", (tf - t0) / (CLOCKS_PER_SEC / 1000));
	print(line, C);

	system("pause");
	return 0;
}
