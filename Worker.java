/*******************************************************************************
/
/      filename:  Worker.java
/
/   description:  Worker class; each thread calculates it's matrix result
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
import java.util.concurrent.Semaphore;

class Worker implements Runnable {

    int i, j, arows, bcols;
    Barrier wall;
    double matrixA[][], matrixB[][], matrixR[][];

    Worker (int i, int j, Barrier wall, double matrixA[][],
     double matrixB[][], double matrixR[][], int arows, int bcols) {
       this.i = i;
       this.j = j;
       this.arows = arows;
       this.bcols = bcols;
       this.wall = wall;
       this.matrixR = matrixR;
       this.matrixA = matrixA;
       this.matrixB = matrixB;
    }

    public void run() {
       work();
    }
    
    void work() {
      //compute each value of NM matrix
      for (int q = 0; q < matrixB.length; q++)
      {
          matrixR[i][j] += matrixA[i][q] * matrixB[q][j];
      }
       // now wait for others
       wall.waitForOthers();
    }
}