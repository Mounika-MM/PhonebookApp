package com.phonebook.app.dao;

/****
 * This class is for all the actions like insert group ,delete group and
 * fetch all the groups from the database.
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

import com.phonebook.app.model.Group;

public class GroupDaoImpl implements GroupDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/****
	 * This method is to add/create a new group to the database.
	 */
	public int saveGroup(Group group) {
		int newId = 0;
		String sql = "INSERT INTO test.group (Name, Type, Location) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, group.getName());
			ps.setString(2, group.getType());
			ps.setString(3, group.getLocation());
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
	 * This method is to delete group from the database.
	 */
	public boolean deleteGroup(Group group) {
		boolean status = false;
		String sql = "DELETE FROM test.group WHERE GroupID = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement(sql);
			ps.setInt(1, group.getId());
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
	 * This method is to fetch all the groups from the database.
	 */
	public List<Group> getAllGroups(String groupIds) {
		String sql = null;
		if (groupIds == null) {
			sql = "SELECT * FROM test.group order by GroupID";
		} else {
			sql = "SELECT * FROM test.group where GroupID in (" + groupIds + ")order by GroupID";
		}

		Connection conn = null;
		PreparedStatement ps = null;

		List<Group> groupsList = new ArrayList<Group>();
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Group group = new Group();
				group.setId(resultSet.getInt("GroupID"));
				group.setName(resultSet.getString("Name"));
				group.setType(resultSet.getString("Type"));
				group.setLocation(resultSet.getString("Location"));
				groupsList.add(group);
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
		return groupsList;
	}
}
