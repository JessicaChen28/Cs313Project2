public class StackArray{
  int [] data;
  int top = -1;
  int maxSize = 10;

  public StackArray(){
    data = new int[maxSize];
  }
  public StackArray(int size){
    maxSize = size;
    data = new int[maxSize];
  }

  public boolean isEmpty(){
    return top ==-1;
  }

  public void push(int x){
    if(top == maxSize - 1){
      int []temp = new int[2*maxSize];
      for(int i = 0; i <maxSize;i++){
          temp[i]=data[i];
        }
        maxSize *= 2;
        data = temp;
      }
      data[++top] = x; // since our top is -1, ++top will make it 0 and current element
    }
    public int peek(){
    if(isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    return data[top];
  }
  public int pop(){
    if(isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    return data[top--];
  }  
}