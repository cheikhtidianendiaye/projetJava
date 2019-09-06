package dao;



import java.util.List;

import entities.Village;

public interface IVillage
{
	public int add(Village v);
	public boolean update(Village v);
	public boolean delete(int idV);
	public List<Village> liste();
	public Village getVillageById(int idV);


}
