/*You have a summer job as a tech support person for a small company. You would like a program which lets you store your list of tickets to work on.
You would like some way of prioritizing support tickets so that you can handle the most urgent first.
You've decided to use a 1-5 scale to rate urgency of tickets. (1=user needs help changing screensaver 5=server on fire)
You'd like a program which lets you enter new support tickets – a description, the person/department who reported the issue, the date reported, and urgency of the issue.
Your program will display the currently open tickets in a list, in ranked order, more urgent at the top
You should be able to close or remove tickets from the list when you have dealt with them
You should be able to close or remove tickets from the list when you have dealt with them. Your program should save resolved tickets to a file.
Your program should be able to store the list of currently open tickets, so they can be re-loaded when you close and reopen the program.
Analysis: Look for nouns/name/things

Program - we'll need a class with a main method to start the program, and create at least some of the other classes: TicketManager
Support tickets – likely to be a class: Ticket
ticket description, the person/department who reported the issue, and urgency of the issue – these may be attributes – variables – of the ticket class
open tickets, closed tickets – an attribute of a ticket – is it open or closed? Or, are these objects? A closed ticket may need to store the closed date, and resolution to the issue too? Think about this one…
ranked order list, a class – either Java library one, or one we write: TicketQueue?
file – persistent files, store on disk. Can use Java classes to help. May need another TicketFileManager class to help?*/

/*Prioritizing support tickets – which part of the application should handle prioritizing?
Enter new support tickets – responsibility of TicketManager?
Display the currently open tickets – responsibility of TicketManager?
Close or remove tickets from the list when you have dealt with them – TicketManager? We need to decide how to handle closed tickets to decide which part of the app will handle closing tickets
Protoype

What's the minimum features we need?
A list of tickets
Able to add tickets
We need a Ticket class
LinkedList is useful, and we know it does much of what we need in TicketQueue. Let's use a LinkedList to store Tickets – let's see if that works for our TicketQueue class
And we'll need a TicketManager class with a main method it
Let's write this (code on next 2 slides)*/

/*App can create tickets and add to a list
But obviously not finished yet
Need a way to sort tickets by priority
And delete tickets
This means the user will need a choice of add ticket; and remove ticket
What to add next?
Choices for the user – a simple menu*/

/*icketManager class – next prototype
- Have added a menu
- Each menu item calls a method
- Add tickets works, show all tickets works
- Delete tickets to be implemented
- Input validation to be implemented
Tickets class is unchanged
Code too big to fit on slide - please select all (ctrl-A or Command-A) and copy
Reflect on this prototype

How do we find a ticket to delete?
Could either search for a string that matches an issue;
That's a lot of typing for longer issues
User could type in a string and search for issues which contain that string?
Example: user types in "Server", will see a list of all tickets with the characters "server" in the issue
Your task in the lab
Or add a unique ID number for each ticket?
We'll probably have to do this anyway, so let's use this way for now
Then add searching by name later*/

/*Ticket
Date dateReported
int priority
String reporter
String description
int ticketID
int staticTicketIDCounter
Deleting ticket by ID

Add a getTicketID method to Ticket
Delete Ticket method will:
Print list of tickets
Ask for ticket ID to delete
Loop over all Tickets in the list, 
and when we find a ticket with that ID, remove from list
Print message if that ticket is not found*/

/*Add ticket(s)
Delete tickets
View current tickets
Quit program*/

/*Ticket ordering reflects the order they were added
We need to order them by priority, no matter when 
they were added – so we can deal with priority=5 issues first
Three options:
add them to the list in the correct order
or add them at the end of the list and then sort the list

leave the list unsorted, display method will search 
the list to retrieve and display the Tickets in order
Or write a Ticket storing data structure which handles 
 tickets in order (or search Java library classes for such a thing)
Or use a different data structure (store a list of 
lists of each priority level ticket?)
Which is the best method?
Storing Tickets: Using a Different Data Structure

How you could store and organize tickets?
As you think about it, consider how your design could 
be adapted if the requirements changed – for example, 
the number of priority levels changes from 1-5, to 1-20?
Sorting Tickets – sorting the LinkedList
Using Java Classes/Interfaces

Modify Ticket so it implements the Comparator interface
A ticket that is also a Comparator can be sorted
Have to write a method that enables LinkedList to 
compare two tickets, so it can sort them
Then LinkedList can sort the tickets into the right order for us
In a future class…
Sorting Tickets – Adding them to the list in the right order

Let's try this method
A little more work for TicketManager
But will work if we have 1-5 or 1-20 priority levels*/

/*Add some sample tickets.
Are they added in order?
Can you delete a ticket?
What do you think about this approach to storing tickets?
Was it the best approach?
Could it be improved?
Do you think you should change the data structure used?
How easy is it to modify if you need to add more features, 
or the requirements change?
package com.gaby;*/


import java.util.*;
public class TicketManager {
public static void main(String[] args) {
LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();
Scanner scan = new Scanner(System.in);
while(true){
System.out.println("1. Enter Ticket\n2. Delete Ticket\n3. Display All Tickets\n4. Quit");
int task = Integer.parseInt(scan.nextLine());
if (task == 1) {
//Call addTickets, which will let us enter any number of new tickets
addTickets(ticketQueue);
} else if (task == 2) {
//delete a ticket
deleteTicket(ticketQueue);
} else if ( task == 4 ) {
//Quit. Future prototype may want to save all tickets to a file
System.out.println("Quitting program");
break;
}
else {
//this will happen for 3 or any other selection that is a valid int
//TODO Program crashes if you enter anything else - please fix
//Default will be print all tickets
printAllTickets(ticketQueue);
}
}
scan.close();
}
protected static void deleteTicket(LinkedList<Ticket> ticketQueue) {
//What to do here? Need to delete ticket, but how do we identify the ticket to delete?
//TODO implement this method
System.out.println("Delete tickets method called");
}
//Move the adding ticket code to a method
protected static void addTickets(LinkedList<Ticket> ticketQueue) {
Scanner sc = new Scanner(System.in);
boolean moreProblems = true;
String description, reporter;
Date dateReported = new Date(); //Default constructor creates date with current date/time
int priority;
while (moreProblems){
System.out.println("Enter problem");
description = sc.nextLine();
System.out.println("Who reported this issue?");
reporter = sc.nextLine();
System.out.println("Enter priority of " + description);
priority = Integer.parseInt(sc.nextLine());
Ticket t = new Ticket(description, priority, reporter, dateReported);
//ticketQueue.add(t);
addTicketInPriorityOrder(ticketQueue, t);
printAllTickets(ticketQueue);
System.out.println("More tickets to add?");
String more = sc.nextLine();
if (more.equalsIgnoreCase("N")) {
moreProblems = false;
}
}
}
protected static void addTicketInPriorityOrder(LinkedList<Ticket> tickets, Ticket newTicket){
//Logic: assume the list is either empty or sorted
if (tickets.size() == 0 ) {//Special case - if list is empty, add ticket and return
tickets.add(newTicket);
return;
}
//Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
//Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end
int newTicketPriority = newTicket.getPriority();
for (int x = 0; x < tickets.size() ; x++) { //use a regular for loop so we know which element we are looking at
//if newTicket is higher or equal priority than the this element, add it in front of this one, and return
if (newTicketPriority >= tickets.get(x).getPriority()) {
tickets.add(x, newTicket);
return;
}
}
//Will only get here if the ticket is not added in the loop
//If that happens, it must be lower priority than all other tickets. So, add to the end.
tickets.addLast(newTicket);
}
protected static void printAllTickets(LinkedList<Ticket> tickets) {
System.out.println(" ------- All open tickets ----------");
for (Ticket t : tickets ) {
System.out.println(t); //Write a toString method in Ticket class
//println will try to call toString on its argument
}
System.out.println(" ------- End of ticket list ----------");
}
}