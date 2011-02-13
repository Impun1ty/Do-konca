package domen.android.school.doKoncaUre;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import domen.android.school.doKoncaUre.Knjiznica;

public class main extends Activity {
    /** Called when the activity is first created. */
	public static short zacetek = 435; //7:15
    EditText ure, minute;
    Button btnOsvezi;
    static Context tole;
    Knjiznica lib = new Knjiznica();
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        
        tole = getApplicationContext();
        
        setContentView(R.layout.main);
       
        ure = (EditText) findViewById(R.id.EditUra);
        minute = (EditText) findViewById(R.id.EditMinute);
        btnOsvezi = (Button) findViewById(R.id.ButtonRefresh);
        
        osvezi();
        
       btnOsvezi.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			osvezi();
		}
	});
    }
    
    private void osvezi(){
    	lib.init();
    	int sedajSUra = lib.trenutnaUra();
    	ure.setText(String.valueOf(sedajSUra));
    	
    	int doKonca = lib.minutDoKonca();
    	if(doKonca <= 0){
    		minute.setTextColor(Color.RED);
    		minute.setText(String.valueOf(5+doKonca));
    	} else {
    		minute.setTextColor(Color.BLACK);
    		minute.setText(String.valueOf(doKonca));
    	}
    }
    public boolean onCreateOptionsMenu(Menu menu) {     
        menu.add(0, 0, 0, "Nastavitve");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 0:
        	Log.v("menu","nastavitve");
        	Intent i = new Intent(main.this, Nastavitve.class);
        	startActivity(i);
        	
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
    
    static public Context taKnjiznica(){
		return tole;
	}
}