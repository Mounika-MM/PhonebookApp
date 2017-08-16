package com.phonebook.app.dao;

/****
 * This class is for all the actions like insert contact ,delete contact and
 * fetch all the contacts from the database.
 * @author Mounika
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.phonebook.app.model.Contact;

public class ContactDaoImpl implements ContactDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/****
	 * This method is to add/create a new contact to the database.
	 */
	public int saveContact(Contact contact) {
		int newId = 0;
		String sql = "INSERT INTO contact (FirstName, Lastname, Home, Mobile, Email, Group_Ids) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getHome());
			ps.setString(4, contact.getMobile());
			ps.setString(5, contact.getEmail());
			ps.setString(6, contact.getGroupIds());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs != null && rs.next()) {
				newId = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return newId;
	}
	
	/****
	 * This method is to delete contact from the database.
	 */
	public boolean deleteContact(Contact contact) {
		boolean status  = false;
		String sql = "DELETE FROM contact WHERE ContactID = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement(sql);
			ps.setInt(1, contact.getId());
			ps.executeUpdate();
			status = true;

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return status;
	}

	/****
	 * This method is to fetch all the contacts from the database.
	 */
	public List<Contact> getAllContacts() {
		String sql = "SELECT * FROM contact order by ContactID";
		Connection conn = null;
		PreparedStatement ps = null;

		List<Contact> contactsList = new ArrayList<Contact>();
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Contact contact = new Contact();
				contact.setId(resultSet.getInt("ContactID"));
				contact.setFirstName(resultSet.getString("FirstName"));
				contact.setLastName(resultSet.getString("LastName"));
				contact.setHome(resultSet.getString("Home"));
				contact.setMobile(resultSet.getString("Mobile"));
				contact.setEmail(resultSet.getString("Email"));
				contact.setGroupIds(resultSet.getString("Group_Ids"));
				
				contactsList.add(contact);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return contactsList;
	}
}
