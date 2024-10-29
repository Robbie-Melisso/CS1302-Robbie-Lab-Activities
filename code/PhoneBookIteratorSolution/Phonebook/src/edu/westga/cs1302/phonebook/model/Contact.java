package edu.westga.cs1302.phonebook.model;
/** Stores information for a contact.
 *
 * @author CS1302
 * @version Fall 2024
 */
public abstract class Contact  implements Comparable<Contact>{
	protected String name;
	protected long phoneNumber;

	/** Creates a new Contact object with the specified name and phoneNumber
	 *
	 * @precondition name != null && name.length() > 2 &&
	 * 				 phoneNumber.length() == 10 && phoneNumber contains only digits (0-9)
	 * @postcondition getName() == name &&
	 * 				  getPhoneNumber() == phoneNumber
	 *
	 * @param name name of the contact
	 * @param phoneNumber 10 digit phone number for the contact
	 */
	public Contact(String name, long phoneNumber){
		if(name == null || name.length() < 3){
			throw new IllegalArgumentException("Invalid name");
		}
		if(!verifyPhoneNumber(phoneNumber)){
			throw new IllegalArgumentException("Phone number is invalid.");
		}
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/** Verifies a phoneNumber is valid.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param phoneNumber the phone number to check
	 *
	 * @return true if the phoneNumber is valid;
	 * 		   false if the phonenNumber is not valid
	 */
	public static boolean verifyPhoneNumber(long phoneNumber) {
		return phoneNumber >= 1000000000 && phoneNumber <= 9999999999l;
	}

	/** Provides access to the contact's name.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the contact's name
	 */
	public abstract String getName();

	/** Provides access to the contact's phoneNumber.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the contact's phoneNumber
	 */
	public long getPhoneNumber(){
		return this.phoneNumber;
	}

	/**Sends a message in the manner appropriate for this Contact.
	 *
	 * @precondition none
	 * @postcondition message has been sent
	 *
	 * @param message message to be sent
	 */
	public abstract void sendMessage(String message);


	@Override
	public int compareTo(Contact otherContact) {
		if(this.name.equals(otherContact.getName())) {
			if(this.phoneNumber == otherContact.getPhoneNumber()) {
				return 0;
			}
			else if(this.phoneNumber > otherContact.getPhoneNumber()) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else {
			return this.name.compareTo(otherContact.getName());
		}
	}
}
