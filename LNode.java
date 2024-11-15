public class LNode {
  int data;
  LNode next;
//constructors
  public LNode(){
    data = 0; next = null;
  }
  public LNode(int data){
    this.data = data; this.next = null;
  }
  public LNode(int data, LNode next){
    this.data = data; this.next = next;
  }
  //sets and gets
  public void setData(int data){
    this.data = data;
  }
  public void setNext(LNode next){
    this.next = next;
  }
  public int getData(){
    return this.data;
  }
  public LNode getNext(){
    return this.next;
  }
}
