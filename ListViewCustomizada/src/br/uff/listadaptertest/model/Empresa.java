package br.uff.listadaptertest.model;

import br.uff.listadaptertest.R;

public class Empresa {
	
	public final static int ANDROID_ICONE = 0;
	public final static int IOS_ICONE = 1;
	public final static int WINDOWS_ICONE = 2;
	public final static int BLACKBERRY_ICONE = 3;
	
	private int logotipo;
	private String sistema;
	private String nome;
	
	public Empresa(int resource, String sistema, String nome)
	{
		this.logotipo = getImagem(resource);
		this.sistema = sistema;
		this.nome = nome;
	}
	
	private int getImagem(int imgResource){
		
		int resource = 0;
		
		switch (imgResource) 
		{
			case ANDROID_ICONE:
				resource = R.drawable.android_small_icon; 
				break;
				
			case IOS_ICONE:
				resource = R.drawable.ios_small_icon; 
				break;
				
			case WINDOWS_ICONE:
				resource = R.drawable.windows_phone_small_icon; 
				break;
				
			case BLACKBERRY_ICONE:
				resource = R.drawable.blackberry_small_icon; 
				break;
	
			default:
				break;
		}
		
		return resource;
	}
	
	public int getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(int logo) {
		this.logotipo = logo;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "[" + this.nome + "] " + this.sistema;
	}
}