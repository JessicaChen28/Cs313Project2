public class QueueList{
  private LNode front;
  private LNode rear;
  private int count = 0;

  public QueueList(){
    front = new LNode();
    rear = front;
  }
  public boolean isEmpty(){
    return(front.next==null);
  }
  public void enQ(int x){
    rear.next = new LNode(x);
    if(front == rear){
      front.next = rear.next;
    }
    rear = rear.next;
    count++;
  }
  public int deQ(){
    if(isEmpty()){
      throw new RuntimeException("Queue is empty");
    }
    int oldFront = front.next.data;
    front.next = front.next.next;
    count--;
    if(count == 0) {
      rear =front;
    }
    return oldFront;
  }
  public int peek(){
    if(isEmpty()){
      throw new RuntimeException("Queue is empty");
    }
    return front.next.data;
  }

}
