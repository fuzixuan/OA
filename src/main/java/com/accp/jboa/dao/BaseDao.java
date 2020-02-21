package com.accp.jboa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @ClassName: BaseDao
 * @Description: TODO
 * @author: wy
 * @date: 2018��4��8�� ����4:30:45
 * @version: V1.0
 */
public class BaseDao {
	private String driver = "com.mysql.jdbc.Driver";// ��������
	private String url = "jdbc:mysql://localhost:3306/jboa";// �����ַ���
	private String user = "root";// ���ݿ��û���
	private String password = "aaa12345";// ���ݿ�����
	protected Connection conn = null;// ���Ӷ���
	protected PreparedStatement stmt = null;// �������

	/**
	 * 
	 * @Title: getConnection
	 * @Description: ��ȡ���ݿ����Ӷ���
	 * @return Connection
	 */
	public Connection getConnection() {
		try {
			Class.forName(driver);// ��������
			conn = DriverManager.getConnection(url, user, password);// ��ȡ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param conn
	 *            ���ݿ����Ӷ���
	 * @param stmt
	 *            �������
	 * @param rs
	 *            ���ݼ�����
	 */
	public void closeAll(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Title: executeUpdate
	 * @Description: ִ����ɾ�Ĳ���
	 * @param sql
	 *            sql���
	 * @param param
	 *            ��������
	 * @return int
	 */
	public int executeUpdate(String sql, Object... param) {
		int num = 0;
		getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					stmt.setObject(i + 1, param[i]);
				}
				num = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stmt, null);
		}
		return num;
	}

	/**
	 * 
	 * @Title: executeQuery
	 * @Description: ִ�в�ѯ����
	 * @param sql
	 *            sql���
	 * @param param
	 *            ��������
	 * @return ResultSet
	 */
	public ResultSet executeQuery(String sql, Object... param) {
		getConnection();
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					stmt.setObject(i + 1, param[i]);
				}
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
