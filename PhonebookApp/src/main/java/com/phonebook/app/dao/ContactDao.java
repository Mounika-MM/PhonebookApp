package com.phonebook.app.dao;

/****
 *This is the interface where we defined saveContact(), getAllContacts(),  
 *deleteContact() methods  for all the contacts
 *@author Mounika
 */


import java.util.List;

import com.phonebook.app.model.Contact;

public interface ContactDao {

	public int saveContact(Contact contact);

	public List<Contact> getAllContacts();
	
	public boolean deleteContact(Contact contact);
}
