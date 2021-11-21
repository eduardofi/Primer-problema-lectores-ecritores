/**
 * Writer.java
 *
 * Escritor para la base de datos.
 *
 */

public class Writer implements Runnable{
  private ReadWriteLock db;
  int writerNum;
  public Writer(int writercount, ReadWriteLock db) {
    this.db = db;
    this.writerNum = writercount;
  }
  public void run() {
    while (true) {
      // siesta por un rato
      SleepUtilities.nap();
      
      System.out.println("escritor " + writerNum + " quiere escribir.");
      db.acquireWriteLock(writerNum);
      
      // ahora escribe en la base de datos
      SleepUtilities.nap();
      
      db.releaseWriteLock(writerNum);
    }
  }
}
