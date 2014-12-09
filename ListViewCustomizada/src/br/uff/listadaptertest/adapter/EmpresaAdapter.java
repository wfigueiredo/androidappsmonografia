package br.uff.listadaptertest.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.uff.listadaptertest.R;
import br.uff.listadaptertest.model.Empresa;

public class EmpresaAdapter extends ArrayAdapter<Empresa> {
	
	private Context context;
	private List<Empresa> empresas;

	public EmpresaAdapter(Context context, int textViewResourceId, List<Empresa> empresas) {
		
		super(context, textViewResourceId, empresas);
		this.context = context;
		this.empresas = empresas;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.list_item_layout, null);
		
		Empresa empresa = empresas.get(position);
		
		ImageView logotipo = (ImageView) view.findViewById(R.id.icone);
		logotipo.setImageResource(empresa.getLogotipo());
		
		TextView sistema = (TextView) view.findViewById(R.id.sistema);
		sistema.setText(empresa.getSistema());
		
		TextView nomeEmpresa = (TextView) view.findViewById(R.id.empresa);
		nomeEmpresa.setText(empresa.getNome());
		
		return view;
	}
}
