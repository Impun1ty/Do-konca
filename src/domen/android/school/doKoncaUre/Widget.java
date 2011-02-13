package domen.android.school.doKoncaUre;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {
	public static short zacetek = 435;
	AppWidgetManager appWidgetManager;
	Knjiznica lib = new Knjiznica();

	public void onReceive(Context context, Intent intent)
	{
	    String action = intent.getAction();
	    if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
	    {
	        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
			AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), views);
			//osvezi(context);
	    }
	    super.onReceive(context, intent);
	}
	public void onUpdate(Context context, AppWidgetManager appWidgetManagerThis, int[] appWidgetIds) {
		Log.v("Widget","update");
		appWidgetManager = appWidgetManagerThis;
		osvezi(context);
		
	}
	private void osvezi(Context context){
		lib.init();
    	int sedajSUra = lib.trenutnaUra();
    	int doKonca = lib.minutDoKonca();
    	RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
    	
    	views.setTextViewText(R.id.WidgetUra, String.valueOf(sedajSUra));
    	views.setTextViewText(R.id.UraZvonenja, lib.casZvonenja());
    	
    	if(doKonca <= 0){
    		views.setTextColor(R.id.WidgetMinute, Color.RED);
    		views.setTextViewText(R.id.WidgetMinute, String.valueOf(5+doKonca));
    	} else {
    		views.setTextColor(R.id.WidgetMinute, Color.BLACK);
    		views.setTextViewText(R.id.WidgetMinute, String.valueOf(doKonca));
    	}
    	appWidgetManager.updateAppWidget(new ComponentName(context, Widget.class), views);
	}
}
