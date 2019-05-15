
public class CustomProcess implements Comparable<CustomProcess> {

	private static int nextProcessId = 1; // stores the id to be assigned to the
											// next process
											// to be created
	private final int PROCESS_ID; // unique identifier for this process
	private int burstTime; // time required by this process for CPU execution

	 /**
	   * Create a new instance CustomProcess with the provided burstTime
	   * @param burstTime burst time for this instance
	   */
	
	public CustomProcess(int burstTime) {

		PROCESS_ID = nextProcessId;
		this.burstTime = burstTime;
		nextProcessId++;
	}

	/**
	 * Return 1 if this instance is bigger, -1 if other is bigger and 1 or -1 if
	 * equal, prioritizing the instance with the lowest PROCESS_ID
	 * 
	 * @param other
	 *            the instance of CustomProcess being compared
	 * @return 1, -1 on comparison
	 */

	public int compareTo(CustomProcess other) {
		if (this.burstTime > other.getBurstTime()) {
			return 1;
		} else if (this.burstTime < other.getBurstTime()) {
			return -1;
		} else {
			if (this.getProcessId() > other.getProcessId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Get the process id of this instance
	 * 
	 * @return Process ID
	 */
	public int getProcessId() {
		return PROCESS_ID;
	}

	/**
	 * Get the burst time of this instance
	 * 
	 * @return Burst Time
	 */
	public int getBurstTime() {
		return burstTime;
	}

	
}
