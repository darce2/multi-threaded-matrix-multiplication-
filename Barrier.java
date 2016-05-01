/*******************************************************************************
/
/      filename:  Barrier.java
/
/   description:  Barrier or wall to ensure all threads reached spot in 
/                 program to synchronize an operation
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
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Barrier{
   ReentrantLock lock;
   Condition threadBlock;
   int thread_count;
   int count;
   boolean blocked;

   Barrier(int thread_count)
   {
      this.thread_count = thread_count;
      count = 0;
      blocked = true;
      lock = new ReentrantLock();
      threadBlock = lock.newCondition();
   }

   //function to wait for all threads
   public void waitForOthers(){
   lock.lock();

      count++;//keep track of how many have invoked this method
      if (count == thread_count)
      { //Last thread has arrived 
      	count = 0;//reset count
      	blocked = false;
      	threadBlock.signalAll();//signal all threads
      }

      //block all threads
      while(blocked) try { threadBlock.await(); } 
      catch (InterruptedException e) {}

   lock.unlock(); 
   }
}