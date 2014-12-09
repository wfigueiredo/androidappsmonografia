package br.uff.tabhosttest;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabHostActivity extends TabActivity {
    
	private TabHost tabHost;
	
	private final String TAB_SPEC_MUSICAS   = "musicas";
	private final String TAB_SPEC_VIDEOS   = "videos";
	private final String TAB_SPEC_FOTOS = "fotos";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_aba);
        
        Resources resources = getResources();
        
        tabHost = getTabHost();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(TAB_SPEC_FOTOS);
        
        // Fotos
        tabSpec.setIndicator(resources.getString(R.string.fotos), 
        					 resources.getDrawable(R.drawable.photo_icon));
        tabSpec.setContent(new Intent(TabHostActivity.this, AbaFotosActivity.class));
        tabHost.newTabSpec(TAB_SPEC_FOTOS);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec(TAB_SPEC_MUSICAS);
        
        // Musicas
        tabSpec.setIndicator(resources.getString(R.string.musicas), 
        					 resources.getDrawable(R.drawable.music_icon));
        tabSpec.setContent(new Intent(TabHostActivity.this, AbaMusicasActivity.class));
        tabHost.newTabSpec(TAB_SPEC_MUSICAS);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec(TAB_SPEC_VIDEOS);
        
        // Videos
        tabSpec.setIndicator(resources.getString(R.string.videos), 
        					 resources.getDrawable(R.drawable.video_icon));
        tabSpec.setContent(new Intent(TabHostActivity.this, AbaVideosActivity.class));
        tabHost.newTabSpec(TAB_SPEC_VIDEOS);
        tabHost.addTab(tabSpec);
        
        tabHost.setCurrentTab(0);
    }
}