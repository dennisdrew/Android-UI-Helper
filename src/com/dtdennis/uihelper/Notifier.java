package com.dtdennis.uihelper;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class Notifier {
	private Context context;
	private NotificationManager mNotificationManager;
	private Notification mNotification;
	
	public Notifier(Context appContext){
		context=appContext;
	}
	
	public void toast(String msg){
		Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
	}
	
	public void alert(String title, String msg, boolean cancelable,
			final Runnable action, int icon) {
		new AlertDialog.Builder(context).setCancelable(cancelable)
				.setIcon(icon).setTitle(title)
				.setMessage(msg)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (action != null)
							action.run();
					}
				}).show();
	}
	
	public void alertQuestion(String title, String msg, boolean cancelable,
			final Runnable posAction, final Runnable negAction, int icon) {
		new AlertDialog.Builder(context)
				.setCancelable(cancelable)
				.setIcon(icon)
				.setTitle(title)
				.setMessage(msg)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								posAction.run();
								dialog.dismiss();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (negAction != null)
							negAction.run();
						dialog.dismiss();
					}
				}).show();
	}
	
	public void notify(int id, int icon, String ticker, String title,
			String subTitle, Intent notificationIntent,
			PendingIntent contentIntent) {
		mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		long when = System.currentTimeMillis();
		mNotification = new Notification(icon, ticker, when);
		mNotification.setLatestEventInfo(context, title, subTitle,
				contentIntent);
		mNotification.flags = Notification.FLAG_AUTO_CANCEL;
		mNotificationManager.notify(id, mNotification);
	}
}
