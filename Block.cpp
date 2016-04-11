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
Arquivo: Block.cpp
Descrição: Operações de multiplicações matriciais em blocos para medida de desempenho.
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
	if (dimension % 2 != 0 || dimension > 2000) {
		system("cls");
		goto start;
	}

	int line = dimension;
	int colun = dimension;
	int **A;
	int **B;
	int **C;
	int **result;
		
	// Memory Allocation for matrix
	A = alloc(line);
	B = alloc(line);
	C = alloc(line);

	// values atribuition
	A = atribuition(line, A);
	B = atribuition(line, B);
	C = matrixzero(line, C);

	// print matrix A and B
	print(line, A);
	print(line, B);

	// Transform the matrix in Blocks and multiplies the A and B Matrixs
	result = buildblock(line, A, B);
	// print the final matrix
	print(line, result);

	system("pause");
	return 0;

}
