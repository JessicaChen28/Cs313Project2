public class LinkedNumberList {
  private LNode head;
  //constructor
  public LinkedNumberList(){ 
    head = new LNode();//create dummy head node to avoid “null pointers”
  }
  public boolean isEmpty(){
    return head.next == null;
  } // avoid branch delay
  public void insertSorted(int x){
    LNode curr = head.next;
    LNode prev = head;
    while ((curr != null) && (x > curr.data)){
      prev = curr; curr = curr.next;
    }
    curr = new LNode(x, curr);
    prev.next = curr;
  }
  public String toString(){
    String s = "";
    LNode curr = head.next;
    while (curr!= null){
      s += curr.data + " ";
      curr = curr.next;
    }
    s += "\n";
    return s;
  }
}

