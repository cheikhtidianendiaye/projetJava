package dao;

import java.sql.ResultSet;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.DriverManager;

import java.sql.Statement;

public class Db
{
	// pour l'ouverture de la connexion a la base de donnees
	private Connection cnx;
	// pour la recuperation des resultat des requetes de type SELECT
	private ResultSet rs;
	// pour l'execution des requetes prepares
	private PreparedStatement pstm;
	// pour la recuperation des requetes de type mis a jour
	private int ok ;


	// ouverture de la base de donnees
	public void getConnexion()
	{
		String url = "jdbc:mysql://localhost:3306/bd";
		String user =  "root";
		String password = "passer";
		try
		{
			// chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url,user,password);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// initialisation des requetes sql prepare
	public void initPrepare(String sql)
	{
		getConnexion();
		try
		{
			if(sql.toLowerCase().startsWith("insert"))
			{
				// recuperation de l'id auto-incrememt
				pstm = cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			}
			else
			{
				pstm = cnx.prepareStatement(sql);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	// execution des requetes de type maj
	public int execuetMaj()
	{
		try
		{
			ok = pstm.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ok;
	}

	// execution des requetes de type select
	public ResultSet executeSelect()
	{
		try
		{
			rs = pstm.executeQuery();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}

	// methode qui permet de fermer la connxion a la base de donnees
	public void closeConnexion()
	{
		try
		{
			if(cnx!=null)
			{
				cnx.close();
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	public PreparedStatement getPstm()
	{
		return pstm;
	}



}
