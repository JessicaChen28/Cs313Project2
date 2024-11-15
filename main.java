import java.io.BufferedReader;
import java.io.FileReader; 
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) {
        StackList stackWidgets = new StackList();
        QueueList waitingList = new QueueList();
        Widget widget = new Widget(0, 0);
        int lastCost = 0;

        StringTokenizer st;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Proj _1_Transactions.txt"));
            String line;
            while((line = br.readLine()) != null){ //goes through the file line by line
                String[] transaction = line.split("\\s+"); //split the line from the file based on spaces, into an array of strings

                if(transaction[0].equals("R")){
                     
                    widget = new Widget(Integer.parseInt(transaction[1]), Integer.parseInt(transaction[2])); //create a new widget object with the price and quantity
                    stackWidgets.push(Integer.parseInt(transaction[1])); //push the number of widgets into the stack
                    lastCost = Integer.parseInt(transaction[2]);
                    System.err.println("Stack: " + stackWidgets.peek());
                    System.err.println("Last Cost: " + lastCost);
                    System.err.println("Recieved " + widget.toString());
                }
                else if (transaction[0].equals("S")){
                    System.out.println("SOLD "+ transaction[1]);
                    int currentShipment = stackWidgets.peek(); //peek the top of the stack
                    if(currentShipment != 0){
                        int remainingShipment = currentShipment - Integer.parseInt(transaction[1]); //subtract the number of widgets being sold from the top of the stack
                        System.err.println("Remaining Shipment: " + remainingShipment);
                        if(remainingShipment == 0){
                            stackWidgets.pop(); //pop the top of the stack, basically removing the shipment
                        } else if(remainingShipment < 0){
                            stackWidgets.pop(); //pop the top of the stack
                            currentShipment = stackWidgets.peek(); //going down to the stack to get the next shipment
                            System.out.println("Current Shipment: " + currentShipment);
                            remainingShipment = currentShipment - Math.abs(remainingShipment); //subtract the remaining shipment from the current shipment
                            currentShipment = remainingShipment; //set the current shipment to the remaining shipment
                            stackWidgets.push(currentShipment); //push the current shipment back into the stack
                            System.out.println("New Remaining Shipment: " + remainingShipment);
                            System.out.println("New Current Shipment: " + stackWidgets.peek());
                            if(stackWidgets.peek() < 0){
                                waitingList.enQ(remainingShipment); //enqueue the remaining shipment into the waiting list
                                System.out.println("Waiting List: " + waitingList.peek());
                            }
                        } else {
                            stackWidgets.pop(); //removes the top 
                            stackWidgets.push(remainingShipment); //pushes the remaining shipment back into the stack
                            System.out.println("New Current Shipment: " 
                            + stackWidgets.peek());
                        }
                    
                    }
                }
            }
            br.close(); //stops reading the file 
        } catch (Exception e) {
            System.out.println("File isn't found"); //needed for the FileReader
        }
    }
}