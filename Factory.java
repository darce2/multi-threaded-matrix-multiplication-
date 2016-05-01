/*******************************************************************************
/
/      filename:  Factory.java
/
/   description:  Spawn Worker threads and define matrices to compute result
/                 Then print resultant matrix after all threads finished 
/
/        author:  D'Arcy, Arlen  
/      login id:  FA_15_CPS356_21
/
/         class:  CPS 356
/    instructor:  Perugini
/    assignment:  Project #3
/
/      assigned:  Novemeber 5, 2015
/           due:  November 19, 2015
/
******************************************************************************/
class Factory {
	//define matrix
   static double matrixA[][] = {{1.0, 0.0, 2.0}, {-1.0, 3.0, 1.0}};
   static double matrixB[][] = {{3.0, 1.0}, {2.0, 1.0}, {1.0, 0.0}};

   // define constants
   static final int AROWS = matrixA.length;
   static final int ACOLS = matrixA[0].length;
   static final int BROWS = matrixB.length;
   static final int BCOLS = matrixB[0].length;
   static final int NUM_WORKERS = AROWS*BCOLS;
   //create resultant matrix
   static double[][] matrixR = new double[AROWS][BCOLS];

   public static void main(String args[]) {
      //plus 1 for the main thread
      Barrier wall = new Barrier(NUM_WORKERS + 1);

      for (int i = 0; i < AROWS; i++) {
         for (int j = 0; j < BCOLS; j++){
            (new Thread(new Worker(i, j, wall, matrixA, matrixB,
             matrixR, AROWS, BROWS))).start();
         }
      }
      //wait for all threads to print
      wall.waitForOthers();

      //print resultant matrix
      for (int k=0; k < AROWS; k++){
         for (int l=0; l < BCOLS; l++){
            System.err.print(matrixR[k][l]+ " ");
         }
         System.err.println();
      }
   }
}