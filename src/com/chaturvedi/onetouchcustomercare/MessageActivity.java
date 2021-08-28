// Shree KRISHNAya Namaha
// Author: Nagabhushan S N

package com.chaturvedi.onetouchcustomercare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public class MessageActivity extends Activity 
{
	private Intent messageIntent;
	private String title;
	private String message;
	private AlertDialog.Builder messageDialog;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		messageIntent=getIntent();
		title=messageIntent.getStringExtra("Title");
		message=messageIntent.getStringExtra("Body");
		messageDialog=new AlertDialog.Builder(this);
		messageDialog.setTitle(title);
		messageDialog.setMessage(message);
		messageDialog.setCancelable(false);
		messageDialog.setPositiveButton("OK", new OKListener());
		AlertDialog alert=messageDialog.create();
		alert.show();
	}
	
	private class OKListener implements OnClickListener
	{

		@Override
		public void onClick(DialogInterface dialog, int which) 
		{
			MessageActivity.this.finish();
		}
		
	}
}
