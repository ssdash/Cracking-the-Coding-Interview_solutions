package __Intro_Prime;

public class Prime {
	/* Solution 1: simple prime checker */
	public static boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/* Solution 2: Sieve of Eratosthenes */
	public static boolean[] generatePrimes(int max) {
		boolean[] flags = new boolean[max + 1];
		initialize(flags);
		int prime = 2;
		int sqrt = (int) Math.sqrt(max);
		while (prime <= sqrt) { // see comment in crossOff() to see why we stop at sqrt(max) 
			crossOff(flags, prime);
			prime = getNextPrime(flags, prime);
		}
		return flags;
	}
	
	private static void initialize(boolean[] flags) {
		flags[0] = false;
		flags[1] = false;
		for (int i = 2; i < flags.length; i++) {
			flags[i] = true;
		}
	}
	
	/* Cross off multiples of prime from our array */
	private static void crossOff(boolean[] flags, int prime) {
		/* We can start with (prime*prime), because if we have k * prime, where k < prime,
		   this value would have already been crossed off in a prior call to this function */
		for (int i = prime*prime; i < flags.length; i += prime) {
			flags[i] = false;
		}
	}
	
	public static int getNextPrime(boolean[] flags, int prime) {
		for (int i = prime + 1; i < flags.length; i++) {
			if (flags[i] == true) {
				return i;
			}
		}
		return -1; // our boolean[] wasn't big enough so we couldn't find the next prime.
	}
}
