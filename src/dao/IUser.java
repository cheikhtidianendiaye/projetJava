package dao;

import entities.User;

public interface IUser
{
	public User getLogin(String email,String password);

}
