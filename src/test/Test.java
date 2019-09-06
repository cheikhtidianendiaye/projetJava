package test;

import java.util.List;

import dao.IVillage;
import dao.VillageDB;
import entities.Village;

public class Test {

	public static void main(String[] args)
	{
		List<Village> lesVillages =  new VillageDB().liste();
		for (Village v : lesVillages)
		{
			System.out.println(v);
			//System.out.println(v.getIdV()+" - "+v.getNomV());
		}

	}

}
