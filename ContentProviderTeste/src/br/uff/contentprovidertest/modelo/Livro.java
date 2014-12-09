package br.uff.contentprovidertest.modelo;

import android.provider.BaseColumns;
import br.uff.contentprovidertest.bd.TabelaLivro;

public class Livro {
	
	private String id;
	
	private String title;
	
	private String publisher;
	
	private String about;
	
	public Livro() {
	}
	
	public Livro(String id, String title, String publisher, String about) {
		
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.about = about;
	}
	
	/**
	 * Inner-class used to help this Entity with ContentProvider issues.
	 *
	 */
	public final static class Books implements BaseColumns {
		
		private Books() {
			
		}
		
		public static final String DEFAULT_SORT_ORDER = TabelaLivro.COLUNA_ID + " ASC";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String toString(){
		return this.title;
	}
}