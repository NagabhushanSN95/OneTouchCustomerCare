package com.chaturvedi.onetouchcustomercare;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity 
{
	private ImageButton docomoBalanceButton;
	private ImageButton docomoInternetButton;
	private ImageButton docomoAccountDetailsButton;
	
	private ImageButton aircelBalanceButton;
	private ImageButton aircelInternetButton;
	private ImageButton aircelAccountDetailsButton;
	
	private SmsManager sms;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sms=SmsManager.getDefault();
		docomoBalanceButton=(ImageButton)findViewById(R.id.docomo_balance);
		docomoBalanceButton.setOnClickListener(new ButtonListener(11));
		docomoInternetButton=(ImageButton)findViewById(R.id.docomo_internet);
		docomoInternetButton.setOnClickListener(new ButtonListener(12));
		docomoAccountDetailsButton=(ImageButton)findViewById(R.id.docomo_account_details);
		docomoAccountDetailsButton.setOnClickListener(new ButtonListener(13));
		
		aircelBalanceButton=(ImageButton)findViewById(R.id.aircel_balance);
		aircelBalanceButton.setOnClickListener(new ButtonListener(21));
		aircelInternetButton=(ImageButton)findViewById(R.id.aircel_internet);
		aircelInternetButton.setOnClickListener(new ButtonListener(22));
		aircelAccountDetailsButton=(ImageButton)findViewById(R.id.aircel_account_details);
		aircelAccountDetailsButton.setOnClickListener(new ButtonListener(23));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void sms(String num, String text)
	{
		sms.sendTextMessage(num, null, text, null, null);
		
	}
	
	private void call(String num)
	{
		try
		{
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:"+num));
			startActivity(callIntent);
		}
		catch (ActivityNotFoundException activityException)
		{
			Log.e("One Touch Customer Care", "Call failed", activityException);
		}
	}
	
	private class ButtonListener implements OnClickListener
	{
		private int sl_no;
		
		public ButtonListener(int sl_no)
		{
			this.sl_no=sl_no;
		}
		@Override
		public void onClick(View v)
		{
			switch(sl_no)
			{
				case 11:
					sms("121", "Bal");
					SmsReceiver.setAction(11);
					break;
				
				case 12:
					sms("121", "Bal");
					SmsReceiver.setAction(12);
					break;
				
				case 13:
					sms("121", "Bal");
					SmsReceiver.setAction(13);
					break;
					
				case 21:
					sms("121", "Bal");
					SmsReceiver.setAction(21);
					break;
				
				case 22:
					call("*122*011%23");
					SmsReceiver.setAction(22);
					break;
					
				case 23:
					sms("121", "Acc");
					SmsReceiver.setAction(23);
					break;
			}
		}
		
	}

}
