package com.example.dataprovider;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class DataProvider {
	String url = "jdbc:mysql://localhost:3306/cboard?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	String jdbcDriver = "java.sql.DriverManager";
	String username = "root";
	String password = "root";

	public Connection getCon() {

		Connection con = null;
		try {
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	public String getData(String sql) {
		Connection con = this.getCon();
		Statement statement = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			statement = con.createStatement();
			rs = statement.executeQuery(sql);
			ResultSetMetaData rm = rs.getMetaData();
			int columns = rm.getColumnCount();
			String[] headers = new String[columns];
			for (int i = 1; i <= columns; i++) {
				headers[i - 1] = rm.getColumnLabel(i);
			}
			while (rs.next()) {
				for (int j = 1; j <= columns; j++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put(headers[j - 1], rs.getString(j));
					list.add(map);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return JSONObject.toJSONString(list);
	}

	/**
	 * 调用无参的存储过程
	 * 
	 * @param sql
	 *            "call myprocedure"
	 */
	public void callProcedure(String sql) {
		Connection con = this.getCon();
		CallableStatement callableStatement = null;
		ResultSet rs = null;

		try {
			callableStatement = con.prepareCall(sql);
			rs = callableStatement.executeQuery();
			System.out.println("**********************************************");
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
			rs.close();
			callableStatement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 调用有参的存储过程
	 * 
	 * @param sql
	 *            CREATE PROCEDURE procedure1(IN SP_name varchar(20)) BEGIN IF
	 *            SP_name=null OR SP_name=' ' THEN SELECT * FROM mytable; ELSE
	 *            SELECT * FROM mytable WHERE name=SP_name; END IF; END;
	 */
	public void callProcedurewithIN(String sql,String in) {
		Connection con = this.getCon();
		CallableStatement cst = null;
		ResultSet rs = null;
		// sql="call myprocedure1(?) ";
		try {
			cst = con.prepareCall(sql);
			cst.setString(1, in);
			rs = cst.executeQuery();
			System.out.println("**********************************************");
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
			rs.close();
			cst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param sql
	 *            CREATE PROCEDURE myprocedure2(OUT SP_name varchar(20)) BEGIN
	 *            SELECT user_name INTO SP_name FROM mytable WHERE id=2; END;
	 */
	public void callProcedurewithOUT(String sql) {
		Connection con = this.getCon();
		CallableStatement cst = null;
		ResultSet rs = null;
		// sql="call myprocedure2(?) ";
		try {
			cst = con.prepareCall(sql);
			cst.registerOutParameter(1, Types.VARCHAR);
			rs = cst.executeQuery();
			System.out.println("**********************************************");
			
			System.out.println(cst.getString(1));
			rs.close();
			cst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param sql
	 *            CREATE PROCEDURE myprocedure3(OUT SP_name varchar(20),IN id1
	 *            int,OUT path1 int) BEGIN SELECT age , name INTO path1, SP_name
	 *            FROM mytable WHERE id=id1; END;
	 */
	public void callProcedurewithIN$OUT(String sql) {
		Connection con = this.getCon();
		CallableStatement cst = null;
		ResultSet rs = null;
		// sql="call myprocedure3(?,?,?) ";
		try {
			cst = con.prepareCall(sql);
			cst.registerOutParameter(1, Types.VARCHAR);
			cst.registerOutParameter(3, Types.INTEGER);
			cst.getParameterMetaData();
			cst.setInt(2, 2);
			rs = cst.executeQuery();
			System.out.println("**********************************************");
			System.out.println(cst.getString(1));
			System.out.println(cst.getInt(3));
			rs.close();
			cst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 关闭自动事务  使用批处理
	 */
	public int[]  Bach(List<String> sqls) {
		Connection con = this.getCon();
		int[] result = new int[] {};
		Statement statement = null;
		try {
			//关闭自动提交
			con.setAutoCommit(false);
			statement = con.createStatement();
			for(String sql : sqls) {
				statement.addBatch(sql);
			}
			result = statement.executeBatch();
			con.commit();
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
		
	}

}
