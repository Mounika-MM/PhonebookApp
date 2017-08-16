package com.phonebook.app.dao;

/****
 *This is the interface where we defined saveGroup(), getAllGroupss(),  
 *deleteGroup() methods  for all the contacts
 *@author Mounika
  */
import java.util.List;

import com.phonebook.app.model.Group;

public interface GroupDao {
	public int saveGroup(Group group);

	public List<Group> getAllGroups(String groupIds);
	
	public boolean deleteGroup(Group group);
}
