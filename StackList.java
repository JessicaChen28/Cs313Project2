public class StackList{
  LNode top;

  public boolean isEmpty(){
    return (top == null);
  }
  public void push(int x){
    top = new LNode(x,top);
  }
  public int pop(){
    if(isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    LNode temp = top;
    int oldTop = temp.data;
    top = top.next;
    temp.next = null;
    return oldTop;
  }
  public int peek(){
    if(isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    return top.data;
  }
}