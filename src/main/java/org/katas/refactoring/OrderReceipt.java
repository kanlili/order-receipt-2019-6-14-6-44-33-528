package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        printHeaders( output);
        printCustomerNameAddress(output);
        printItem(output);
        printItem(output);
        printTotSalesTx();
        printTotTotalAmount();
        // prints the state tax
        output.append("Sales Tax").append('\t').append(printTotSalesTx());

        // print total amount
        output.append("Total Amount").append('\t').append(printTotTotalAmount());
        return output.toString();
    }
    public void printHeaders(StringBuilder output){
        output.append("======Printing Orders======\n");
    }
    public void printCustomerNameAddress(StringBuilder output){
        output.append(o.getCustomerName()).append(o.getCustomerAddress());
    }
    public void printItem(StringBuilder output){
        for (LineItem lineItem : o.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');
        }
    }
    public double printTotSalesTx(){
       return o.getLineItems().stream().map(LineItem::totalAmount).mapToDouble(item->item* .10).reduce(0,(a,b)->a+b);
    }
    public double printTotTotalAmount(){
        return o.getLineItems().stream().map(LineItem::totalAmount).mapToDouble(item->item).reduce(0,(a,b)->a+b)+printTotSalesTx();
    }
}