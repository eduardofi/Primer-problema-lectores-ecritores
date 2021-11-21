/**
 * Database.java
 *
 * Esta clase contiene los mecanismos de acceso para los lectores
 * y escritores a la base de datos.
 */


public class Database implements ReadWriteLock{
  private int readerCount;
  private boolean dbWriting;

  public Database() {
    // Número de lectores activos
    readerCount = 0;
    // variable  para saber si se esta escribiendo en la base de datos
    dbWriting = false;
  }
  
  public synchronized void acquireReadLock(int readerNum) {
    /** 
    * Mientra un escritor este utilizando la base de datos el lector 
    * tiene que esperar
    */
    while (dbWriting == true) {
      try {
        wait();
      }
      catch(InterruptedException e) { }
    }
    ++readerCount;

    System.out.println("lector " + readerNum 
      + " está leyendo. Lectores Activos = " + readerCount);
  }

  public synchronized void releaseReadLock(int readerNum) {
    --readerCount;
    /**
    * El último lector indica que
    * la base de datos ya no se esta leyendo.
    */
    if (readerCount == 0)
      notify();
    System.out.println("Lector " + readerNum 
      + " ha terminado de leer. Lectores  Activos = " + readerCount);
  } 

  public synchronized void acquireWriteLock(int writerNum) {
    /**
    * Mientras haya lectores activos o bien un escritor 
    * esté escribiendo en la base de datos,
    * éste lector se bloquea.
    */
    while (readerCount > 0 || dbWriting == true) {
      try {
        wait();
      }
      catch(InterruptedException e) { }
    }
    /**
    * Una vez que no haya lectores ni escritores,
    * este lector indica que está escribiendo en la base de datos.
    */
    System.out.println("escritor " + writerNum + " está escribiendo.");
    dbWriting = true;
  }
  
  public synchronized void releaseWriteLock(int writerNum) {
    dbWriting = false;
    System.out.println("escritor " + writerNum 
      + " ha terminado de escribir.");
    notifyAll();
  }
}
