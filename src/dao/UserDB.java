package dao;

import java.sql.ResultSet;

import entities.User;
import entities.Village;

public class UserDB implements IUser
{
	private Db db ;
	private ResultSet rs;
	public UserDB()
	{
		db = new Db();
	}


	@Override
	public User getLogin(String email, String password)
	{
		User u = null;
		String sql = "select * from user where email = ? and pass = ?";
		try
		{
			db.initPrepare(sql);
			db.getPstm().setString(1, email);
			db.getPstm().setString(2, password);
			rs = db.executeSelect();
			while(rs.next())
			{
				u = new User();
				u.setId(rs.getInt(1));
				u.setNom(rs.getString(2));
				u.setPrenom(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setCivilite(rs.getString(6));
				u.setProfil(rs.getString(7));
			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return u;
	}
}
