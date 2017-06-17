package mypackage;

interface Counter {
	int next();
}

// comment added
public class LocalInnerClass {

	//private static Test monitor = new Test();
	private int count = 0;

	// Factory method for getting Counter object
	Counter getCounter(final String name) {

		String xString = "compile error when it used within the local inner class";
		final String yString = "can be used when it used within the local inner class";

		// Define a local inner class within the getCounter() method
		class LocalCounter implements Counter {

			// Constructor method of the LocalCounter Local class
			public LocalCounter() {
				// Local inner class can have a constructor
				System.out.println("LocalCounter()");

				// Compile error - cannot access non-final local variable
				// in the method the local class is defined
				//System.out.println(xString);
				System.out.println(yString);
			}

			public int next() {
				System.out.print(name); // Access local final
				return count++;
			}
		}
		return new LocalCounter();
	}

	// Factory method for getting Counter object
	// We use an anonymous inner class here for doing the
	// same thing.
	Counter getCounter2(final String name) {

		return new Counter() {
			// Anonymous inner class cannot have a named
			// constructor, only an instance initializer:
			{
				System.out.println("Counter()");
			}

			// Implement next() method of Counter Interface
			public int next() {
				System.out.print(name); // Access local final
				return count++;
			}
		};
	}

	// Main method for testing
	public static void main(String[] args) {
		LocalInnerClass lic = new LocalInnerClass();

		// Get counter using Local inner class
		Counter c1 = lic.getCounter("Local inner ");
		for (int i = 0; i < 5; i++) {
			System.out.println(c1.next());
		}

		// Get counter using anonymous inner class
		Counter c2 = lic.getCounter2("Anonymous inner ");
		for (int i = 0; i < 5; i++) {
			System.out.println(c2.next());
		}

	}
}
