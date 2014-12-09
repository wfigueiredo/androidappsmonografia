package br.uff.handlertest;

import android.app.Activity;
import android.os.Bundle;

public class ActivityPrincipal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
}