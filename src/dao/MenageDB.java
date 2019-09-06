package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import entities.Menage;
import entities.Village;

public class MenageDB implements IMenage
{
	private Db db;
	private int ok;
	private ResultSet rs;
	private boolean bol;

	public MenageDB()
	{
		db = new Db();
	}


	@Override
	public int add(Menage m)
	{
		String sql = "insert into Menage values(null,?,?)";
		try
		{
			db.initPrepare(sql);
			db.getPstm().setString(1, m.getNomFamille());
			db.getPstm().setInt(2, m.getIdV().getIdV());
			ok = db.execuetMaj();
			rs = db.getPstm().getGeneratedKeys();
			while (rs.next())
			{
				ok = rs.getInt(1);
			}
			rs.close();
			db.closeConnexion();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return ok;
	}

	@Override
	public boolean update(Menage m)
	{
		String sql = "update village set nomFamille = ? , idV = ? where idM = ?";
		bol = false;
		try
		{
			db.initPrepare(sql);
			db.getPstm().setString(1, m.getNomFamille());
			db.getPstm().setInt(2, m.getIdV().getIdV());
			db.getPstm().setInt(3, m.getIdM());
			ok = db.execuetMaj();
			if(ok!=0)
			{
				bol = true;
			}

		} catch (Exception e)
		{
			bol = false;
			e.printStackTrace();
		}
		return bol;
	}

	@Override
	public boolean delete(int idM)
	{
		String sql = "delete from menage where idM = ?";
		bol = false;
		try
		{
			db.initPrepare(sql);
			db.getPstm().setInt(1,idM);
			ok = db.execuetMaj();
			if(ok!=0)
			{
				bol = true;
			}
		} catch (Exception e)
		{
			bol = false;
			e.printStackTrace();
		}
		return bol;
	}

	@Override
	public List<Menage> liste()
	{

		List<Menage> l_menage = new ArrayList<>();
		String sql = " select * from menage";
		try
		{
			db.initPrepare(sql);
			rs = db.executeSelect();
			while(rs.next())
			{
				Menage m = new Menage();
				m.setIdM(rs.getInt(1));
				m.setNomFamille(rs.getString(2));
				m.setIdV(new Village(rs.getInt(3)));
				l_menage.add(m);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return l_menage;
	}


	@Override
	public Menage getMenageById(int idM)
	{
		Menage m = null;
		String sql = "select * from menage where idM = ?";
		try
		{
			db.initPrepare(sql);
			db.getPstm().setInt(1, idM);
			rs = db.executeSelect();
			while(rs.next())
			{
				m = new Menage();
				m.setIdM(rs.getInt(1));
				m.setNomFamille(rs.getString(2));
				m.setIdV(new Village(rs.getInt(3)));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return m;
	}

}
