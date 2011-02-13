package domen.android.school.doKoncaUre;

import java.util.Date;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Knjiznica extends Application{
	Date sedaj;
	int sedajNi, zacetek, minuteKoncaZadnjic;
	String konecTextZadnjic = "";
	
	public Knjiznica() {
		
	}
	
	public void init(){
		Context con = main.taKnjiznica();
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(con);
		zacetek = settings.getInt("zacetek", 435);		
	}
	
	public int trenutnaUra(){
		update();
		
		int sedajSUra = (int) (Math.floor((sedajNi-zacetek)/50)+1);
		if(sedajSUra < 0)
			sedajSUra = 0;
		
		return sedajSUra;
	}
	
	public int minutDoKonca(){
		update();
		
		int doKonca = (int) (45-((sedajNi-zacetek)%50));
		return doKonca;
	}
	public String casZvonenja(){
		int minuteKonca = sedajNi+minutDoKonca();
		
		if(minutDoKonca() <= 0) // odmor
			minuteKonca += 5;
		
		if(minuteKonca != minuteKoncaZadnjic){
			minuteKoncaZadnjic = minuteKonca;
			int uraKonca = (int) (Math.floor(minuteKonca)/60);
			int minutaKonca = (int)(minuteKonca%60);
			
			String minuteText;
			
			if(minutaKonca != 0){
				minuteText = String.valueOf(minutaKonca);
			} else {
				minuteText = "00";
			}
			konecTextZadnjic = String.valueOf(uraKonca)+":"+minuteText;
		}
		return konecTextZadnjic;	
	}
	private void update(){
		sedaj = new Date();
		sedajNi = (short) ((sedaj.getHours()*60) + sedaj.getMinutes());
	}
}