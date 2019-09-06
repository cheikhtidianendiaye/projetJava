package dao;

import java.util.List;

import entities.Menage;



public interface IMenage
{
	public int  add(Menage m);
	public boolean update(Menage m);
	public boolean delete(int idM);
	public List<Menage> liste();
	public Menage getMenageById(int idM);


}
