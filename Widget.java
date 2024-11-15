
public class Widget {
  int cost = 0;
  int numberOfWidgets = 0;
  int totalCost = 0;

  public Widget(int quantity, int price){
    numberOfWidgets = quantity;
    cost = price;
  }

  //methods
  public int getWidget(){
    return numberOfWidgets;
  }
  public void setWidget(int price){
    cost = price;
  }

  public String soldString(){
    return numberOfWidgets + " widgets sold @ " + (cost*1.25);
  }

  @Override
  public String toString(){
    return numberOfWidgets + " widgets @ " + cost + " Total Cost: $" +(double)(cost*numberOfWidgets)/100;
  }
}
