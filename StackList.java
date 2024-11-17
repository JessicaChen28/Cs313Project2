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
      System.out.println("Stack is empty");
    }
    LNode temp = top;
    int oldTop = temp.data;
    top = top.next;
    temp.next = null;
    return oldTop;
  }
  public int peek(){
    if(isEmpty()){
      System.out.println("Stack is empty");
    }
    return top.data;
  }
}