

import java.util.Scanner;

public class ProcessScheduler {

	private int currentTime; // stores the current time after the last run
	private int numProcessesRun; // stores the number of processes run so far
	private CustomProcessQueue queue; // this processing unit's custom process
										// queue

	/**
	 * Constructs sets up the CustomProcessQueue queue, and initializes both
	 * currentTime and numProcessRun to zero.
	 */

	public ProcessScheduler() {

		queue = new CustomProcessQueue();
		this.currentTime = 0;
		this.numProcessesRun = 0;

	}

	/**
	 * This class implement the Process schedualer.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		boolean run = false;
		ProcessScheduler schedule = new ProcessScheduler();
		System.out.println("==========   Welcome to the SJF Process Scheduler App   ========");
		System.out.println(); // print start message.
		while (!run) {
			promptUser();
			String input = myScanner.nextLine().toLowerCase();
			String[] userInput = input.split(" ");
			if (userInput[0].equals("quit") || userInput[0].equals("q")) {
				exitlog(schedule);
				break;
			} 
			else
			{
				if (userInput[0].equals("schedule") || userInput[0].equals("s")) {
					if (Integer.parseInt(userInput[1]) <= 0) {
						System.out.println("WARNING: burst time MUST be greater than 0!\n");
						continue;
					}
					schedule.scheduleProcess(new CustomProcess(Integer.parseInt(userInput[1])));
				} else if (userInput[0].equals("run") || userInput[0].equals("r")) {
					System.out.println(schedule.run());
				} else {
					System.out.println("WARNING: Please enter a valid command!\n");
				}
			}

		}

	}

	/**
	 * enqueue the given process in the CustomProcessQueue queue
	 * 
	 * @param process
	 */
	public void scheduleProcess(CustomProcess process) {
		queue.enqueue(process);
		System.out.println(
				"Process ID " + process.getProcessId() + " scheduled. Burst time = " 
		+ process.getBurstTime() + "\n");
	}

	/**
	 * starts running the ready processes already scheduled in the queue
	 * according to the SJF scheduling policy. This method returns when all the
	 * scheduled processes are run and the queue is empty.
	 * 
	 * @return a String that represents the log of one run operation.
	 */

	public String run() {
		String result = "";
		 int numofprocess = queue.size();

		    if (queue.size() > 1)
		      result += "Starting " + numofprocess + " processes\n\n";
		    else
		      result += "Starting " + numofprocess + " process\n\n";


		    for (int x = 0; x < numofprocess; x++) {

			CustomProcess temp = queue.dequeue();
			result += "Time " + currentTime + " : Process ID " + temp.getProcessId() + 
					" Starting.\n";
			currentTime += temp.getBurstTime();
			numProcessesRun++;
			result += "Time " + currentTime + " : Process ID " + temp.getProcessId() + 
					" Completed.\n";
		}
		result += "\nTime " + currentTime + " : All scheduled processes completed.\n";
		return result;
	}

	/**
	 * This class promttUser that include and print basic log
	 */

	private static void promptUser() {
		System.out.println("Enter command:");
		System.out.println("[schedule <burstTime>] or [s <burstTime>]");
		System.out.println("[run] or [r]");
		System.out.println("[quit] or [q]");
		System.out.println();
	}

	/**
	 * This class exit log that include and print basic log
	 */

	private static void exitlog(ProcessScheduler p) {
		System.out.println(p.numProcessesRun + " processes run in " +
				p.currentTime + " units of time!");
		System.out.println("Thank you for using our scheduler!");
		System.out.println("Goodbye!");
	}

}