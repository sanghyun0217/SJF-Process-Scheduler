
public interface WaitingQueueADT<T extends Comparable<T>> {

 
  public void enqueue(T newObject); // inserts a newObject in the priority queue
 
  public T dequeue(); // removes and returns the item with the highest priority
 
  public T peek(); // returns without removing the item with the highest priority
 
  public int size(); // returns size of the waiting queue
 
  public boolean isEmpty(); // checks if the waiting queue is empty
}