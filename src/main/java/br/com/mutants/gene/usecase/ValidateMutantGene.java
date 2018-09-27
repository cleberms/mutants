package br.com.mutants.gene.usecase;

import org.springframework.stereotype.Service;

@Service
public class ValidateMutantGene {

    private static final String[] MUTANT_DNA = {"AAAA", "CCCC", "GGGG", "TTTT"};

    private static final int[] TREE_DIRECTIONS_X = new int[] { 0, 0, 1, -1, 1, 1, -1, -1 };
    private static final int[] TREE_DIRECTIONS_Y = new int[] { 1, -1, 0, 0, 1, -1, 1, -1 };

    private static final int matrixNumber = 6;

    public boolean isMutant(String[] dnaSequence){

        boolean isMutant = false;

        for(int i = 0 ; i < MUTANT_DNA.length ; i++) {

            isMutant = findSequentialGene(dnaSequence, MUTANT_DNA[i]);

            if(isMutant){
                return true;
            }
        }

        return isMutant;
    }

    private boolean findSequentialGene(String[] dnaSequence ,String mutantSequence) {

        char[][] matrix = convertToMatrx(dnaSequence);

        for (int i = 0; i < matrixNumber; ++i) {
            for (int j = 0; j < matrixNumber; ++j) {
                if (matrix[i][j] == mutantSequence.charAt(0)) {
                    if (verifyTreeDirection(i, j, mutantSequence, matrix)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private char[][] convertToMatrx(String[] dna) {
        char[][] matrix = new char[6][6];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = dna[i].charAt(j);
            }
        }
        return matrix;
    }

    private boolean verifyTreeDirection(int initX, int initY, String word, char[][] matrix) {

        for (int dirIndex = 0; dirIndex < TREE_DIRECTIONS_X.length; ++dirIndex) {
            boolean wordMatches = true;
            for (int charIndex = 0; charIndex < word.length() && wordMatches; ++charIndex) {
                int x = initX + TREE_DIRECTIONS_X[dirIndex] * charIndex;
                int y = initY + TREE_DIRECTIONS_Y[dirIndex] * charIndex;
                if (x < 0 || y < 0 || x >= matrixNumber || y >= matrixNumber || matrix[x][y] != word.charAt(charIndex)) {
                    wordMatches = false;
                }
            }

            if (wordMatches) {
                return true;
            }
        }

        return false;
    }
}
