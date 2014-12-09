package br.uff.sqlitetest.bd.datasource;

import android.content.Context;

public class DataSourceFactory {
	
	public static SistemaMobileDataSource createOperationSystemDataSource(Context context) {
		
		return new SistemaMobileDataSource(context);
	}

}
