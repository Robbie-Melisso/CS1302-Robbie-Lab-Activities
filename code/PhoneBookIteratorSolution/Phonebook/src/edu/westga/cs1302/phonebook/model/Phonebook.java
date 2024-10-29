package edu.westga.cs1302.phonebook.model;
/** Stores and manages a collection of contacts.
*
* @author CS1302
* @version Fall 2024
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/** Manages all contacts stored in a Phonebook
 *
 * @author CS 1302
 * @version Fall 2023
 */
public class Phonebook implements Iterable<Contact>{
	public static final String UNKOWN_CALLER = "UNKOWN CALLER";
	private ArrayList<Contact> contacts;

	/** Creates an initially empty phonebook.
	 *
	 * @precondition none
	 * @postcondition size() == 0
	 *
	 */
	public Phonebook(){
		this.contacts = new ArrayList<Contact>();
	}

	/** Returns the collection of contacts stored in the phonebook.
	 *
	 *  @precondition none
	 *  @postcondition none
	 *
	 * @return the collection of contacts stored in the phonebook
	 */
	public ArrayList<Contact> getContacts(){
		return this.contacts;
	}

	/** Returns the number of contacts in the phonebook.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the number of contacts in the phonebook
	 */
	public int size(){
		return this.contacts.size();
	}

	/** Adds a new contact to the phonebook.
	 *
	 * @precondition contact != null
	 * @postcondition size() == size()@pre+1
	 *
	 * @param contact
	 */
	public void addContact(Contact contact){
		if(contact == null){
			throw new IllegalArgumentException("Invalid contact");
		}
		this.contacts.add(contact);
	}

	/** Gets the name of the contact with the specified phone number.
	 * Returns "UNKNOWN CALLER" if a contact with the phone number
	 * is not found.
	 *
	 * @precondition phoneNumber is valid
	 *
	 * @param contactPhoneNumber
	 *
	 * @return name of the contact with the specified phone number
	 */
	public String getContactName(long contactPhoneNumber){
		if(!Contact.verifyPhoneNumber(contactPhoneNumber)){
			throw new IllegalArgumentException("Contact phone number is invalid.");
		}
		for(Contact contact : this.contacts){
			if(contact.getPhoneNumber() == contactPhoneNumber){
				return contact.getName();
			}
		}
		return Phonebook.UNKOWN_CALLER;
	}

	/** Invites all contacts to the specified event
	 *
	 * @precondition none
	 * @postcondition all contacts have been invited to the event.
	 *
	 * @param eventName name of the event
	 */
	public void inviteContacts(String eventName) {
		for(Contact contact : this.contacts) {
			contact.sendMessage("You are invited to " + eventName);
		}
	}

	/** Sorts the contained list of contacts.
	 *
	 * @precondition none
	 * @postcondition getContacts() is sorted
	 */
	public void sort() {
		Collections.sort(this.contacts);
	}

	@Override
	public Iterator<Contact> iterator() {
		return new PhonebookIterator(this.contacts);
	}

}
