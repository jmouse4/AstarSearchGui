/**
 * Collects debug data and prints to console
 * @author Jerry Moua
 *
 */
public final class Log 
{
	private static final boolean DEBUG_ON = true;
	
	public static void debug(String message)
	{
		if(DEBUG_ON){
			System.out.printf("[Log] %s\n", message);
		}
	}
}
