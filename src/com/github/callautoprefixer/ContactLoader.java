package com.github.callautoprefixer;

import android.content.ContextWrapper;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class ContactLoader {
	private ContextWrapper _context;
	
	public ContactLoader(ContextWrapper context) {
		_context = context;
	}
	
	public Contact getContactByUri(Uri uri) {
		Cursor c = _context.getContentResolver().query(uri, null, null,
				null, null);
		try {
			if (!c.moveToFirst()) {
				return null;
			}
			
			String name = c.getString(c.getColumnIndex(
					ContactsContract.Contacts.DISPLAY_NAME));
			
			int id = c.getInt(c.getColumnIndex(
					ContactsContract.Contacts._ID));
			PhoneNumber number = getPhoneNumber(id);
			
			return new Contact(name, number);
		} finally {
			c.close();
		}
	}

	private PhoneNumber getPhoneNumber(int id) {
		Cursor c = _context.getContentResolver().query(
				ContactsContract.Data.CONTENT_URI,
				new String[] { 
						ContactsContract.Data._ID, Phone.NUMBER,
						Phone.TYPE, Phone.LABEL },
				String.format(
						"%s = ? AND %s = '%s'",
						ContactsContract.Data.CONTACT_ID,
						ContactsContract.Data.MIMETYPE,
						Phone.CONTENT_ITEM_TYPE),
				new String[] { String.valueOf(id) },
				null);
		try {
			if (!c.moveToFirst()) {
				return null;
			}
			String number = c.getString(c.getColumnIndex(Phone.NUMBER));
			return new PhoneNumber(number);
		} finally {
			c.close();
		}
	}
}
