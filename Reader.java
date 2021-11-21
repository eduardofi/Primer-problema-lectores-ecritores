/**
 * Reader.java
 *
 * Lector para la base de datos.
 *
 */

public class Reader implements Runnable{
  
  private ReadWriteLock db;
  int readerNum;

  public Reader(int readerNum, ReadWriteLock db) {
    this.db = db;
    this.readerNum = readerNum;
  }
   
  public void run() {
    while (true) {
      // siesta por un rato
      SleepUtilities.nap();

      System.out.println("lector " + readerNum + " quiere leer.");
      db.acquireReadLock(readerNum);
      // ahora lee de la base de datos
      SleepUtilities.nap();
   
      db.releaseReadLock(readerNum);
    }
  }
}
