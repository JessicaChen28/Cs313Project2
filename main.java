import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) {
        StackList stackWidgets = new StackList();
        QueueList waitingList = new QueueList();
        Widget widget = new Widget(0, 0);
        int lastCost = 0;

        StringTokenizer st;
        try {
            BufferedReader input = new BufferedReader(new FileReader("Proj _1_Transactions.txt"));
            BufferedWriter outputs = new BufferedWriter(new FileWriter("Output.txt"));
            String line;
            while((line = input.readLine()) != null){ //goes through the file line by line
                String[] transaction = line.split("\\s+"); //split the line from the file based on spaces, into an array of strings

                if(transaction[0].equals("R")){
                     
                    widget = new Widget(Integer.parseInt(transaction[1]), Integer.parseInt(transaction[2])); //create a new widget object with the price and quantity
                    stackWidgets.push(Integer.parseInt(transaction[1])); //push the number of widgets into the stack
                    lastCost = Integer.parseInt(transaction[2]);
                    System.err.println("Stack: " + stackWidgets.peek());
                    System.err.println("Last Cost: " + lastCost);
                    System.err.println("Recieved " + widget.toString());
                    outputs.write("Received " + widget.toString() + "\n");

                    //Waiting List
                    //is the waiting not empty and widgets are greater than 0
                    while(!waitingList.isEmpty() && widget.getWidget() > 0){
                        int firstInQueue = waitingList.deQ(); 
                        System.out.println("First in Waiting List: " + firstInQueue);
                        int currentShipment = stackWidgets.pop(); //peek the top of the stack
                        int remainingShipment = currentShipment - (Math.abs(firstInQueue)); //subtract the first in queue from the top of the stack
                        if(remainingShipment > 0){ //leftovers put it back in stack
                            stackWidgets.push(remainingShipment);
                            System.out.println("Finished waiting order: " + firstInQueue);
                            System.out.println("Remaining Shipment: " + remainingShipment);
                            stackWidgets.push(remainingShipment);
                            System.out.println("This is the new top after shipment: " + stackWidgets.peek());
                        } 
                        // else if (remainingShipment < 0){
                        //     waitingList.enQ(remainingShipment); //enqueue the remaining shipment into the waiting list
                        //     System.out.println("Partially finished waiting order: " + firstInQueue);
                        //     System.out.println("Remaining Shipment: " + remainingShipment);
                        //     stackWidgets.push(remainingShipment);
                        //     System.out.println("This is the new top after shipment: " + stackWidgets.peek());
                        // } else {
                        //     System.out.println("Completely finished exactly:" + firstInQueue);
                        // }
                    }
                } else if (transaction[0].equals("S")){
                    System.out.println("SOLD "+ transaction[1]);
                    int orders = Integer.parseInt(transaction[1]);
                    if(stackWidgets.peek() == 0){ // how to check if stack is empty
                        System.out.println("There's no more widgets in stock");
                        waitingList.enQ(orders); //enqueue the number of widgets being sold into the waiting list
                        System.out.println("Waiting List: " + waitingList.peek());
                    }
                    int currentShipment = stackWidgets.peek(); //peek the top of the stack
                    System.out.println("Should match up with top Current Shipment: " + currentShipment);
                    
                    if(stackWidgets.isEmpty()){ // how to check if stack is empty
                        waitingList.enQ(orders); //enqueue the number of widgets being sold into the waiting list
                        System.out.println("Waiting List: " + waitingList.peek());
                    }
                    else if(currentShipment != 0 && !stackWidgets.isEmpty()){
                        System.out.println("Current Shipment: " + currentShipment);
                        int remainingShipment = currentShipment - orders; //subtract the number of widgets being sold from the top of the stack
                        System.err.println("Remaining Shipment: " + remainingShipment);
                        if(remainingShipment == 0){
                            stackWidgets.pop(); //pop the top of the stack, basically removing the shipment
                        } else if(remainingShipment < 0){
                            System.out.println("Top of Stack before pop: " + stackWidgets.peek());
                            stackWidgets.pop(); //pop the top of the stack
                            System.out.println("Top of Stack after pop: " + stackWidgets.peek());
                            currentShipment = stackWidgets.peek(); //going down to the stack to get the next shipment
                            System.out.println("Current Shipment: " + currentShipment);
                            remainingShipment = currentShipment - Math.abs(remainingShipment); //subtract the remaining shipment from the current shipment
                             if(currentShipment > 0){ 
                                // stackWidgets.pop(); //pop the top of the stack
                                if(remainingShipment > 0){ //not negative
                                    stackWidgets.pop();
                                    // System.out.println("Stack top after pop: " + stackWidgets.peek());
                                    stackWidgets.push(remainingShipment); //push the remaining shipment back into the stack
                                    // System.out.println("New Current Shipment in STACK: " + stackWidgets.peek());
                                    // stackWidgets.pop();
                                    System.out.println("New Current Shipment in STACK after push: " + stackWidgets.peek());
                                    widget.setWidget(orders);
                                    System.out.println("Sold: " + widget.soldString());
                                    outputs.write("Sold " + widget.soldString() + "\n");
                                }else{ //negative
                                    System.out.println("NEGATIVE Stack top before pop: " + stackWidgets.peek());
                                    stackWidgets.pop();
                                    waitingList.enQ(remainingShipment); //enqueue the remaining shipment into the waiting list
                                    System.out.println("Waiting List: " + waitingList.peek());
                                    stackWidgets.push(0); //nothing in the stack, unsure if this is correct way
                                    outputs.write("Backorder: " + widget.waitingString() + "\n");
                                    // System.out.println("Stack top after pop: " + stackWidgets.peek());
                                    // System.out.println("Waiting List: " + waitingList.peek());
                                    // System.out.println("This stack should be empty: " + stackWidgets.peek());
                                }
                                // if(stackWidgets.peek() < 0){ //if the top is negative, remove it from the stack
                                //     waitingList.enQ(stackWidgets.peek()); //enqueue the remaining shipment into the waiting list
                                //     System.out.println("Waiting List: " + waitingList.peek());
                                //     System.out.println("This stack should be empty: " + stackWidgets.peek());
                                //     if(stackWidgets.peek() < 0){
                                //         stackWidgets.pop(); //stack is now empty but now we have errors, how do we go back to the top
                                //     }
                                // }
                            // } else if(remainingShipment < 0 && stackWidgets.peek() > 0){ //if we still have remaining,and the stack is not empty
                            //     System.out.println("Is this even working?");
                            // }
                            // System.out.println("New Remaining Shipment: " + remainingShipment);
                            // System.out.println("New Current Shipment: " + stackWidgets.peek()); 
                            // if(stackWidgets.peek() < 0){
                            //     waitingList.enQ(remainingShipment); //enqueue the remaining shipment into the waiting list
                            //     System.out.println("Waiting List: " + waitingList.peek()); 
                             }
                        } else {
                            System.out.println("Stack top before pop: " + stackWidgets.peek());
                            stackWidgets.pop(); //removes the top 
                            System.out.println("Stack top after pop: " + stackWidgets.peek());
                            stackWidgets.push(remainingShipment); //pushes the remaining shipment back into the stack
                            System.out.println("New Current Shipment after push: " + stackWidgets.peek());
                            widget.setWidget(orders);
                            System.out.println("Sold: " + widget.soldString());
                            outputs.write("Sold " + widget.soldString() + "\n");
                        }
                    
                    }
                }
            }
            input.close(); //stops reading the file 
            outputs.close(); //stops writing to the file
        } catch (FileNotFoundException e) {
            System.out.println("File isn't found"); //needed for the FileReader
        } catch (Exception e) {
            e.printStackTrace(); //needed for the BufferedReader
        }
    }
}