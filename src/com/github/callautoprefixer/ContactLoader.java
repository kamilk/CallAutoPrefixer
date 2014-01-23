package com.github.callautoprefixer;

import java.util.List;
import java.util.Vector;

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
			List<PhoneEntry> phoneEntries = getPhoneEntries(id);
			if (phoneEntries.isEmpty()) {
				return null;
			}
			
			return new Contact(name, phoneEntries);
		} finally {
			c.close();
		}
	}

	private List<PhoneEntry> getPhoneEntries(int id) {
		Cursor c = queryContactData(id);
		try {
			Vector<PhoneEntry> entries = new Vector<PhoneEntry>();
			while (c.moveToNext()) {
				String number = c.getString(c.getColumnIndex(Phone.NUMBER));
				
				PhoneEntryType entryType = getPhoneEntryType(c);
				entries.add(new PhoneEntry(new PhoneNumber(number), entryType));
			}
			return entries;
		} finally {
			c.close();
		}
	}

	private Cursor queryContactData(int id) {
		return _context.getContentResolver().query(
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
	}

	private PhoneEntryType getPhoneEntryType(Cursor c) {
		int type = c.getInt(c.getColumnIndexOrThrow(Phone.TYPE));
		if (type == Phone.TYPE_CUSTOM) {
			return new PhoneEntryType(c.getString(c.getColumnIndex(Phone.LABEL)));
		} else {
			return PhoneEntryType.fromAndroidContactType(type);
		}
	}
}
