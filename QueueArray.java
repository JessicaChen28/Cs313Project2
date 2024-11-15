public class QueueArray{
  int [] data;
  int front = 0;
  int rear = -1;
  int count = 0;
  int maxSize = 10;

  public QueueArray(){
    data = new int[maxSize];
  }
  public QueueArray(int size){
    maxSize = size;
    data = new int[maxSize];
  }
  public boolean isEmpty(){
    return count == 0;
  }

  public void enQ(int x){
    if(count == maxSize){
      int [] temp = new int[2*maxSize];
      int j = front;
      for(int i = 0 ;i <count;i++){
        temp[i]=data[j];
        j = (j+1)% maxSize;
      }
      rear = count -1; 
      front = 0;
      data = temp;
      maxSize *= 2;
    }
    rear = (rear+1)%maxSize;
    data[rear]= x;
    count++; //since we added to the Queue
  }
  public int deQ(){
    if(isEmpty()){
      throw new RuntimeException("Queue is empty");
    }
    count--;//since we removed from the Queue
    return data[front++];
  }
}