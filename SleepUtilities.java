/**
 * Utilidades para hacer dormir un hilo.
*/

public class SleepUtilities
{
  private static final int NAP_TIME = 5;
	/**
	 * Siesta entre 0 y NAP_TIME segundos.
	 */
	public static void nap() {
		nap(NAP_TIME);
	}

	/**
	 * Siesta entre 0 y duration segundos.
	 */
	public static void nap(int duration) {
    int sleeptime = (int) (duration * Math.random() );
    try { Thread.sleep(sleeptime*1000); }
    catch (InterruptedException e) {}
	}
}

