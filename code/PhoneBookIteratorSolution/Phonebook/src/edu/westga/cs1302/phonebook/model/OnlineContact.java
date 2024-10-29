package edu.westga.cs1302.phonebook.model;
/** Stores information for an online contact.
 *
 * @author CS1302
 * @version Fall 2024
 */
public class OnlineContact extends Contact {

	private String username;

	/** Creates a new OnlineContact object with the specified name, username, and phoneNumber
	 *
	 * @precondition name != null && name.length() > 2 &&
	 * 				 username != null && username.length() > 2 && username does not contain whitespace &&
	 * 				 phoneNumber.length() == 10 && phoneNumber contains only digits (0-9)
	 * @postcondition getUsername() == username &&
	 * 				  getName() == name+":"+username &&
	 * 				  getPhoneNumber() == phoneNumber
	 *
	 * @param name name of the online contact
	 * @param username username of the online contact
	 * @param phoneNumber 10 digit phone number for the online contact
	 */
	public OnlineContact(String name, String username, long phoneNumber){
		super(name, phoneNumber);
		if(username == null || username.length() < 1){
			throw new IllegalArgumentException("Invalid username");
		}
		for(char character : username.toCharArray()) {
			if(Character.isWhitespace(character)){
				throw new IllegalArgumentException("Username may not contain whitespace");
			}
		}
		this.username = username;
	}


	/** Creates a new OnlineContact object with the specified username and phoneNumber
	 *
	 * @precondition username != null && username.length() > 2 && username does not contain whitespace &&
	 * 				 phoneNumber.length() == 10 && phoneNumber contains only digits (0-9)
	 * @postcondition getUsername() == username &&
	 * 				  getName() == username+":"+username &&
	 * 				  getPhoneNumber() == phoneNumber
	 *
	 * @param username username of the online contact
	 * @param phoneNumber 10 digit phone number for the online contact
	 */
	public OnlineContact(String username, long phoneNumber) {
		this(username, username, phoneNumber);
	}


	/** Provides access to the online contact's name and username.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return <contact's name>:<contact's username>
	 */
	@Override
	public String getName(){
		return this.name + ":" + this.username;
	}

	/** Provides access to the online contact's username
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the online contact's username
	 */
	public String getUsername(){
		return this.username;
	}

	@Override
	public void sendMessage(String message) {
		System.out.println("Emailed to " + this.name + " at " + this.username + "@aol.com: " + message);
	}

}
