package edu.westga.cs1302.phonebook.model;

/** Stores information for a phone contact.
*
* @author CS1302
* @version Fall 2024
*/
public class PhoneContact extends Contact {

	/** Creates a new PhoneContact object with the specified name and phoneNumber
	 *
	 * @precondition name != null && name.length() > 2 &&
	 * 				 phoneNumber.length() == 10 && phoneNumber contains only digits (0-9)
	 * @postcondition getName() == name+":"+username &&
	 * 				  getPhoneNumber() == phoneNumber
	 *
	 * @param name name of the online contact
	 * @param phoneNumber 10 digit phone number for the online contact
	 */
	public PhoneContact(String name, long phoneNumber) {
		super(name, phoneNumber);
	}

	/** Provides access to the contact's name.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the contact's name
	 */
	@Override
	public String getName(){
		return this.name;
	}
	
	/**Sends a message in the manner appropriate for this Contact.
	 *
	 * @precondition none
	 * @postcondition message has been sent
	 *
	 * @param message message to be sent
	 */
	@Override
	public void sendMessage(String message) {
		System.out.println("Texted to " + this.name + " at " + this.phoneNumber + ": " + message);
	}

}
