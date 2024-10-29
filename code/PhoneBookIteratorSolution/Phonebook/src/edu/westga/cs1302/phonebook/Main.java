package edu.westga.cs1302.phonebook;
/** Launch point for the system.
*
* @author CS1302
* @version Fall 2024
*/
import edu.westga.cs1302.phonebook.model.Contact;
import edu.westga.cs1302.phonebook.model.OnlineContact;
import edu.westga.cs1302.phonebook.model.PhoneContact;
import edu.westga.cs1302.phonebook.model.Phonebook;

public class Main {

	public static void main(String[] args) {

		System.out.println("**************POLYMORPHISM**********************");

		Phonebook contactList = new Phonebook();
		contactList.addContact(new PhoneContact("Rory", 1234567890));
		contactList.addContact(new OnlineContact("Amy", "Amelia", 2345678901l));
		contactList.addContact(new OnlineContact("River", "Melody", 3456789012l));
		contactList.addContact(new PhoneContact("Clara", 4567890123l));
		contactList.addContact(new PhoneContact("Handles", 5678901234l));

		//TODO change the loop below to iterate over the Phonebook directly
		for(Contact contact : contactList) {
			System.out.println(contact.getName());
		}




	}

}
