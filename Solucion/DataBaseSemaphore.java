/**
 * DataBaseSemaphhore.java
 *
 * Esta clase contiene los mecanismos de acceso para los lectores
 * y escritores a la base de datos.
 */


import java.util.concurrent.Semaphore;

public class DataBaseSemaphore implements ReadWriteLock
{
  // Número de lectores activos.
  private int readerCount;
  
  /**  
  * El semáforo mutex se utiliza para garantizar la 
  * exclusión mutua cuando readerCount se actualiza. 
  */
  private Semaphore mutex;
  /*
  * El semáforo db funciona como una exclusión mutua
  * para los escritores, solo debe estar un escritor
  * escribiendo.
  */
  private Semaphore db;

  public DataBaseSemaphore() {
    readerCount = 0;
    mutex = new Semaphore(1);
    db = new Semaphore(1);
  }

  public void acquireReadLock(int readerNum) {
    try {
      mutex.acquire();
      /**
      * El primer lector indica que la base 
      * esta siendo leida
      */
      ++readerCount;
      if (readerCount == 1)
        db.acquire();
      System.out.println("lector " + readerNum 
        + " está leyendo. Lectores Activos = " + readerCount);
    } catch (Exception e) {

    }
    finally{
      mutex.release();
    }
  }

  public void releaseReadLock(int readerNum) {
    try {
      mutex.acquire();
      /**
      * El último lector indica que
      * la base de datos ya no se esta leyendo.
      */
      --readerCount;
      if (readerCount == 0)
        db.release();
      System.out.println("Lector " + readerNum 
        + " ha terminado de leer. Lectores  Activos = " 
        + readerCount);
    } catch (Exception e) {

    }
    finally{
      mutex.release();
    }
  }
  public void acquireWriteLock(int writerNum) {   
    try {
      db.acquire();
      System.out.println("escritor " + writerNum 
        + " está escribiendo.");
    } catch (Exception e) {} 
  }
  public void releaseWriteLock(int writerNum) {
    try {
      System.out.println("escritor " + writerNum 
        + " ha terminado de escribir.");
      db.release();
    } catch (Exception e) {} 
  }
}