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
Arquivo: MatrixAndThreads.cpp
Descrição: Operações de multiplicações matriciais com uso de algoritmo paralelo para medida de desempenho.
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
	if (dimension < 2 || dimension > 2000) {
	system("cls");
	goto start;
	}
	int line  = dimension;
	int colun = dimension;
	int **A;
	int **B;
	int **C;
	clock_t t0, tf;
	float time;

	// Memory Allocation for matrix
	A = alloc(line);
	B = alloc(line);
	C = alloc(line);

	// values atribuition
	A = atribuition(line,A);
	B = atribuition(line,B);
	C = matrixzero(line, C);

	// print matrix A and B
	print(line, A);
	print(line, B);

	// A and B multiplication
	t0 = clock();
	C = multiplication(line, A, B);
	tf = clock();

	// print result and Elapsed Time
	print(line, C);
	printf("Elapsed Time: %d ms\n",(tf - t0) / (CLOCKS_PER_SEC / 1000));

	system("pause");
	return 0;

}
