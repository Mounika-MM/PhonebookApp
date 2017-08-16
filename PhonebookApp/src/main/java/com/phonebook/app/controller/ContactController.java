 package com.phonebook.app.controller;
 
 /****
  * This class is for all the actions of contacts like create Contact,
  *  delete contact,fetch all the contacts from the database
  *  @author Mounika
  */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phonebook.app.dao.ContactDao;
import com.phonebook.app.dao.GroupDao;
import com.phonebook.app.model.Contact;
import com.phonebook.app.model.Group;

@Controller
public class ContactController {

	@Autowired
	private ContactDao contactDao;

	@Autowired
	private GroupDao groupDao;

	/****
	 * This method set the contacts to the model object and displays to the UI
	 * @param model
	 */
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String showContacts(ModelMap model) {
		return "Contacts";

	}
/****
 * This method gets all the contacts from the database 
 * and set the contacts to the model object and returns to the UI.
 * @param model
 */
	@RequestMapping(value = "/contacts.do", method = RequestMethod.GET)
	public @ResponseBody List<Contact> getContacts(ModelMap model) {

		List<Contact> allContacts = contactDao.getAllContacts();

		List<Contact> updAllContacts = new ArrayList<Contact>();
		String grpIdsStr = "";
		List<Group> allGroups = null;
		for (Contact contact : allContacts) {
			grpIdsStr = "";
			if (contact.getGroupIds() != null && contact.getGroupIds().trim().length() > 0) {
				allGroups = groupDao.getAllGroups(contact.getGroupIds());

				for (Group group : allGroups) {
					grpIdsStr += group.getName() + ",";
				}

				if (grpIdsStr.length() > 0) {
					grpIdsStr = grpIdsStr.substring(0, grpIdsStr.length() - 1);
				}

			}

			contact.setGroupNames(grpIdsStr);

			updAllContacts.add(contact);
		}

		return updAllContacts;

	}

	/****
	 * This method shows the page to create a new contact.
	 * @param model
	 */
	@RequestMapping(value = "/createcontact", method = RequestMethod.GET)
	public String showCreateForm(ModelMap model) {

		List<Group> allGroups = groupDao.getAllGroups(null);
		model.addAttribute("groupsList", allGroups);

		return "NewContact";

	}

	/****
	 * This method shows the delete contact page.
	 * @param model
	 */
	@RequestMapping(value = "/deletecontact", method = RequestMethod.GET)
	public String showDeleteForm(ModelMap model) {

		return "DeleteContact";

	}

	/****
	 * This method takes the contact as input and returns the same contact object
	 * by setting the newId.
	 * @param contact
	 */
	@RequestMapping(value = "/createcontact.do")
	public @ResponseBody Contact createContact(@RequestBody Contact contact) {

		int newId = contactDao.saveContact(contact);
		contact.setId(newId);
		return contact;

	}

	/****
	 * This method deletes the contact from the contacts list.
	 * @param contact
	 */
	@RequestMapping(value = "/deletecontact.do")
	public @ResponseBody Contact deleteContact(@RequestBody Contact contact) {

		boolean status = contactDao.deleteContact(contact);
		contact.setStatus(status);
		return contact;

	}
}
