/**
 * Interface para el bloqueo de lector-escritor.
 *
 * Pasamos el número que identifica a cada lector y/o escritor
 * en cada método para poder escribir en consola algunos mensajes.
 */

public interface ReadWriteLock
{
  public void acquireReadLock(int readerNum);
  public void acquireWriteLock(int writerNum);
  public void releaseReadLock(int readerNum);
  public void releaseWriteLock(int writerNum);
}