/*Ticket
Can make Ticket do all the work of assigning ticketID number, 
using a static counter shared between all tickets, 
and an instance variable which will be unique to all tickets. 
Assign ticketID in the constructor. 
Also need to modify toString so that when we print the ticket info, the ID is included.*/

/*Add code to the deleteTicket method; so if user enters a ticket ID that doesn’t exist, 
 * it prints an error message and asks the user to try again.
You should also validate that the user is not entering a String or 
double or something that is not an int. Again; the method should ask 
the user to enter the ticket ID again.)

Problem 3:
Modify the menu. Remove the Delete Ticket option. 
Replace it with 3 new options: Delete by ID, Delete by Issue, and Search by Name.
Write ONE method, which searches your ticket list and 
returns a new list of Tickets whose descriptions contain a certain string. 
For example, you might want to search for all tickets with the word “server” in. 
Your method should return a list of all Tickets with “server” in the description.
Use this method to help you implement Search by Name.

Problem 4:
Now, implement Delete by Issue. 
Your user may want to search for all tickets with"Server" 
in the description, to see a list of those tickets and their IDs. 
At that point, they could enter an ID of the Ticket they want to delete.

Problem 5:
Modify your program so that Tickets can store another date 
– resolution date – and aString that documents why the 
ticket was closed – the fix or the resolution for the ticket.
Now assume that when users delete a ticket, it has been 
resolved in some way. Either you’ve fixed the problem, 
 the user has figured out how to change their own 
 screensaver, or it’s become a non-issue in some other way.
Now, when you delete a Ticket, your program should
 ask the user for the resolution. It should store 
 the resolution, plus the current date. Now, 
 remove this Ticket from the ticketQueue list.
And, add this ticket to a new list, called resolvedTickets.
There are at least two ways of doing this:
Question: Would you rather subclass Ticket 
and create a new class calledResolvedTicket? 
Or modify the current Ticket class to add 
these two new variables? Why did you choose the approach that you used?
Problem 6: When the program closes, please 
write out all the data about all open tickets, 
and all resolved tickets, to files.
Resolved tickets should go into one file. 
This file should have today’s date in the 
filename. Something like 
“Resolved_tickets_as_of_february_14_2014.txt” perhaps?
Open tickets should go in another 
file called “open_tickets.txt”.
Problem 7: When you program opens, it 
should look for a file called open_tickets.txt. 
Read in this file, and create ticket objects, 
and store these in the ticketQueue list so the 
user can see all open tickets.
Problem 8: What happens to ticket IDs when the 
program is closed and opened? Make sure they don't 
reset to 1 when the user restarts the program.
*/


package com.gaby;

import java.util.Date;

import java.util.Date;
public class Ticket {
private int priority;
private String reporter; //Stores person or department who reported issue
private String description;
private Date dateReported;
//STATIC Counter - accessible to all Ticket objects.
//If any Ticket object modifies this counter, 
all Ticket objects will have the modified value
//Make it private - only Ticket objects should have access
private static int staticTicketIDCounter = 1;
//The ID for each ticket - instance variable. Each Ticket will have it's own ticketID variable
protected int ticketID;
public Ticket(String desc, int p, String rep, Date date) {
this.description = desc;
this.priority = p;
this.reporter = rep;
this.dateReported = date;
this.ticketID = staticTicketIDCounter;
staticTicketIDCounter++;
}
protected int getPriority() {
return priority;
}
public String toString(){
return("ID= " + this.ticketID + " Issued: " + this.description + " Priority: " + this.priority + " Reported by: "
+ this.reporter + " Reported on: " + this.dateReported);
}

protected static void deleteTicket(LinkedList<Ticket> ticketQueue) {
printAllTickets(ticketQueue); //display list for user
if (ticketQueue.size() == 0) { //no tickets!
System.out.println("No tickets to delete!\n");
return;
}
Scanner deleteScanner = new Scanner(System.in);
System.out.println("Enter ID of ticket to delete");
int deleteID = deleteScanner.nextInt();
//Loop over all tickets. Delete the one with this ticket ID
boolean found = false;
for (Ticket ticket : ticketQueue) {
if (ticket.getTicketID() == deleteID) {
found = true;
ticketQueue.remove(ticket);
System.out.println(String.format("Ticket %d deleted", deleteID));
break; //don't need loop any more.
}
}
if (found == false) {
System.out.println("Ticket ID not found, no ticket deleted");
//TODO – re-write this method to ask for ID again if not found
}
printAllTickets(ticketQueue); //print updated list
}

}