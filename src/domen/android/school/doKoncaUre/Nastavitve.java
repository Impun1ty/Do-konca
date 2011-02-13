package domen.android.school.doKoncaUre;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class Nastavitve extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final SharedPreferences settings = getPreferences(0);
        int sedajZacetek = settings.getInt("zacetek", 435);
        Log.v("zacetek",String.valueOf(sedajZacetek));
        int sedajUra = (int) Math.floor(sedajZacetek/60);
        int sedajMinuta = (int) (sedajZacetek%60);
        Log.v("sedajMinuta",String.valueOf(sedajMinuta));
        
        setContentView(R.layout.nastavitve);
        
        final TimePicker casovnik = (TimePicker) findViewById(R.id.TimePicker01);
        casovnik.setIs24HourView(true); 
        casovnik.setCurrentHour(sedajUra);
        casovnik.setCurrentMinute(sedajMinuta);
        
        Button shrani = (Button) findViewById(R.id.ButtonShrani);
    	shrani.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
	        	int ura = casovnik.getCurrentHour();
	        	int minuta = casovnik.getCurrentMinute();
	        	
	        	
	        	SharedPreferences.Editor ed = settings.edit();

	        	int zacetek = (ura*60)+minuta;
	        	ed.putInt("zacetek", zacetek);
	        	ed.commit();
	        	
	        	finish();
			}
		});
	}
}
