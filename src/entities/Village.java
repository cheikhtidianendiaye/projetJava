package entities;

public class Village
{
	private int idV;
	private String nomV;
	public int getIdV() {
		return idV;
	}
	public void setIdV(int idV) {
		this.idV = idV;
	}
	public String getNomV() {
		return nomV;
	}
	public void setNomV(String nomV) {
		this.nomV = nomV;
	}


	public Village(int idV)
	{
		super();
		this.idV = idV;
	}
	public Village()
	{
		super();
	}


	public String toString()
	{
		return this.getNomV();
		//return this.getIdV() + "-" + this.getNomV();
	}







}
