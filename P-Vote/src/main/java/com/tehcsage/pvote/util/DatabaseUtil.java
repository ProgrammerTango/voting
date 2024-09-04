package com.tehcsage.pvote.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DatabaseUtil {
	
	private final DataSource dataSource;
	
	public void generateAndSendPass(String username) throws Exception {
		String password = UserUtil.generateRandomString(4).toUpperCase();
		try {
			UserUtil.sendEmail(username, "Pvote Password", "The following is your password: \n\n" + password + "\n\nRegards.");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("UPDATE test_user SET password = ?, is_active = ? WHERE username = ? AND is_active = ?");
		statement.setString(1, password);
		statement.setString(2, "Y");
		statement.setString(3,  username);
		statement.setString(4, "N");
		
		statement.executeUpdate();
		
		connection.close();
		
	}
	
	public void castUserVote(String username, String chairVote, String viceVote, String secVote, String treasVote) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("UPDATE users SET chair_vote = ?, vice_vote = ?, sec_vote = ?, treas_vote = ?, has_voted = ?, vote_time = ? WHERE username = ?");
		statement.setString(1, chairVote);
		statement.setString(2, viceVote);
		statement.setString(3,  secVote);
		statement.setString(4, treasVote);
		statement.setString(5, "Y");
		statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
		statement.setString(7, username);
		
		statement.executeUpdate();
		
		connection.close();
	}
	
	public boolean checkVoted(String username) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet res = null;

	    try {
	        connection = dataSource.getConnection();
	        statement = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ? AND has_voted = ?");
	        statement.setString(1, username);
	        statement.setString(2, "Y");
	        
	        res = statement.executeQuery();
	        
	        if (res.next()) {
	            int count = res.getInt(1);
	            return count > 0;
	        } else {
	            return false;
	        }
	    } finally {
	        // Ensure resources are closed to avoid memory leaks
	        if (res != null) {
	            res.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}
	
	public boolean checkUser(String username) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet res = null;

	    try {
	        connection = dataSource.getConnection();
	        statement = connection.prepareStatement("SELECT * FROM test_user WHERE username = ?");
	        statement.setString(1, username);
	        
	        res = statement.executeQuery();
	        
	        if (res.next()) {
	            int count = res.getInt(1);
	            return count > 0;
	        } else {
	            return false;
	        }
	    } finally {
	        // Ensure resources are closed to avoid memory leaks
	        if (res != null) {
	            res.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}

	
	public boolean checkUserActive(String username) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet res = null;

	    try {
	        connection = dataSource.getConnection();
	        statement = connection.prepareStatement("SELECT COUNT(*) FROM test_user WHERE username = ? AND is_active = ?");
	        statement.setString(1, username);
	        statement.setString(2, "Y");
	        
	        res = statement.executeQuery();
	        
	        if (res.next()) {
	            int count = res.getInt(1);
	            return count > 0;
	        } else {
	            return false;
	        }
	    } finally {
	        // Ensure resources are closed to avoid memory leaks
	        if (res != null) {
	            res.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}

}
