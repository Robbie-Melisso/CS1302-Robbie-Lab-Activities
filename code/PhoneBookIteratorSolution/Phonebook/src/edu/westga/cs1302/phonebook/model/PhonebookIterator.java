package edu.westga.cs1302.phonebook.model;

import java.util.Iterator;
import java.util.List;

public class PhonebookIterator implements Iterator<Contact> {
	
	private List<Contact> contacts;
	private int currentIndex;
	
	public PhonebookIterator(List<Contact> contacts) {
		if (contacts == null) {
			throw new IllegalArgumentException();
		}
		this.contacts = contacts;
		this.currentIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return this.currentIndex < this.contacts.size();
	}

	@Override
	public Contact next() {
		Contact contact = this.contacts.get(this.currentIndex);
		this.currentIndex++;
		return contact;
	}

}
