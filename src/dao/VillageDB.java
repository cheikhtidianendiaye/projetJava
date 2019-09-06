package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import entities.Village;

public class VillageDB implements IVillage
{
	private Db db;
	private int ok;
	private ResultSet rs;
	private boolean bol;

	public VillageDB()
	{
		db = new Db();
	}

	@Override
	public int add(Village v)
	{
		String sql = "insert into village values(null,?)";
		try
		{
			// initialisation
			db.initPrepare(sql);
			// passage de valeur
			db.getPstm().setString(1, v.getNomV());
			// execution
			ok = db.execuetMaj();
			// gestion des cles primaires apres auto_increment
			rs = db.getPstm().getGeneratedKeys();
			while(rs.next())
			{
				ok = rs.getInt(1);
			}
			// fin gestion des cles primaires auto_increment
			rs.close(); // liberer la memoire
			db.closeConnexion();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean update(Village v)
	{
		String sql = "update village set nomV = ? where idV = ? ";
		bol = false;
		try
		{
			db.initPrepare(sql);
			db.getPstm().setString(1, v.getNomV());
			db.getPstm().setInt(2, v.getIdV());
			ok = db.execuetMaj();
			if(ok!=0)
			{
				bol = true;
			}
		} catch (Exception e)
		{
			bol = false ;
			e.printStackTrace();
		}
		return bol;
	}

	@Override
	public boolean delete(int idV)
	{
		String sql = "delete from village where idV = ?";
		bol = false;
		try
		{
			db.initPrepare(sql);
			db.getPstm().setInt(1, idV);
			ok = db.execuetMaj();
			if(ok!=0)
			{
				bol = true;
			}
		} catch(Exception e)
		{
			bol = false;
			e.printStackTrace();
		}
		return bol;
	}

	@Override
	public List<Village> liste()
	{
		List<Village> l_village = new ArrayList<>();
		String sql = "select * from village";
		try
		{
			db.initPrepare(sql);
			rs = db.executeSelect();
			while(rs.next())
			{
				Village v = new Village();
				v.setIdV(rs.getInt(1)); // v.setIdV(rs.getInt("idV"));
				v.setNomV(rs.getString(2)); // v.setIdV(rs.getInt("nomV"));
				l_village.add(v);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return l_village;
	}

	@Override
	public Village getVillageById(int idV)
	{
		Village v = null;
		String sql = "select * from village where idV = ?";
		try
		{
			db.initPrepare(sql);
			db.getPstm().setInt(1, idV);
			rs = db.executeSelect();
			while(rs.next())
			{
				v = new Village();
				v.setIdV(rs.getInt(1));
				v.setNomV(rs.getString(2));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return v;
	}


}
