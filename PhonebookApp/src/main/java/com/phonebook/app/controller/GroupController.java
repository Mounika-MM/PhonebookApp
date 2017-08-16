
package com.phonebook.app.controller;

/****
 * This class is for all the actions of Groups like create Group,
 *  delete Group,fetch all the list of groups from the database
 *  @author Mounika
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phonebook.app.dao.GroupDao;
import com.phonebook.app.model.Group;

@Controller
public class GroupController {

	@Autowired
	private GroupDao groupDao;
	
	/****
	 * This method set the groups to the model object and displays to the UI.
	 * @param model
	*/
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String showGroups(ModelMap model) {
		return "Groups";

	}
	
	/****
	 * This method gets all the gropus from the database 
	 * and set the groups to the model object and returns to the UI.
	 * @param model
	 */
	@RequestMapping(value = "/groups.do", method = RequestMethod.GET)
	public @ResponseBody List<Group> getGroups(ModelMap model) {

		List<Group> allGroups = groupDao.getAllGroups(null);
		return allGroups;
	}	

	/****
	 * This method shows the page to create a new group.
	 * @param model
	 */
	@RequestMapping(value = "/creategroup", method = RequestMethod.GET)
	public String showCreateForm(ModelMap model) {

		return "NewGroup";

	}
	
	/****
	 * This method shows the delete group page.
	 * @param model
	 */
	@RequestMapping(value = "/deletegroup", method = RequestMethod.GET)
	public String showDeleteForm(ModelMap model) {

		return "DeleteGroup";

	}

	
	/****
	 * This method takes the group as input and returns the same group object
	 * by setting the newId.
	 * @param group
	 */
	@RequestMapping(value = "/creategroup.do")
	public @ResponseBody Group createGroup(@RequestBody Group group) {

		int newId = groupDao.saveGroup(group);
		group.setId(newId);
		return group;

	}
	
	/****
	 * This method deletes the group from the groups list.
	 * @param group
	 */
	@RequestMapping(value = "/deletegroup.do")
	public @ResponseBody Group deleteGroup(@RequestBody Group group) {

		boolean status = groupDao.deleteGroup(group);
		group.setStatus(status);
		return group;

	}	
}
