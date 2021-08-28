// Shree KRISHNAya Namaha
// Author: Nagabhushan S N

package com.chaturvedi.onetouchcustomercare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver
{
	private String sender;
	private String message;
	
	private static int action;
	private String balance;
	private String validity;
	
	private Intent messageIntent; 
	@Override
	public void onReceive(Context context, Intent intent)
	{
		//---get the SMS message passed in---
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		if (bundle != null)
		{
			//---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i=0; i<msgs.length; i++)
			{
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				if (i==0) 
				{
					//---get the sender address/phone number---
					sender= msgs[i].getOriginatingAddress();
					//---get the message body---
					message=msgs[i].getMessageBody().toString();
				}
			}
			if(msgs[0].getOriginatingAddress().equals("121") && message.equalsIgnoreCase("Your request received."))
			{
				abortBroadcast();
			}
			else if(msgs[0].getOriginatingAddress().equals("TA-DOCOMO"))
			{
				abortBroadcast();
				messageIntent=new Intent(context, MessageActivity.class);
				messageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				switch(action)
				{
					case 11:
						balance="Balance= Rs"+message.substring(10, 17);
						validity="Validity= "+message.substring(29, 40);
						message=balance+"\n"+validity;
						messageIntent.putExtra("Title", "Balance Query");
						messageIntent.putExtra("Body", message);
						context.startActivity(messageIntent);
						break;
						
					case 12:
						balance="Data Left= "+message.substring(68, 74)+"MB";
						validity="Validity= "+message.substring(86, 97);
						message=balance+"\n"+validity;
						messageIntent.putExtra("Title", "Data Query");
						messageIntent.putExtra("Body", message);
						context.startActivity(messageIntent);
						break;
						
					case 13:
						messageIntent.putExtra("Title", "Account Query");
						messageIntent.putExtra("Body", message);
						context.startActivity(messageIntent);
						break;
						
					default:
						messageIntent.putExtra("Title", "Account Query");
						messageIntent.putExtra("Body", message);
						context.startActivity(messageIntent);
						break;
				}
			}
			else if(msgs[0].getOriginatingAddress().equals("DA-AIRCEL"))
			{
				abortBroadcast();
				messageIntent=new Intent(context, MessageActivity.class);
				messageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				switch(action)
				{
					case 21:
						balance="Balance= Rs"+message.substring(43, 49);
						validity="Validity= "+message.substring(77, 88);
						message=balance+"\n"+validity;
						messageIntent.putExtra("Title", "Balance Query");
						messageIntent.putExtra("Body", message);
						context.startActivity(messageIntent);
						break;
					
					case 22:
						break;
					
					case 23:
						messageIntent.putExtra("Title", "Account Query");
						messageIntent.putExtra("Body", message);
						context.startActivity(messageIntent);
						break;
						
					default:
						messageIntent.putExtra("Title", "Account Query");
						messageIntent.putExtra("Body", message);
						context.startActivity(messageIntent);
						break;
				}
			}
		}
	}
	
	public static void setAction(int act)
	{
		action=act;
	}
}
