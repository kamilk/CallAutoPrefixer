package com.github.callautoprefixer;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	final static int SELECT_CONTACT = 101;
	
	private TextView _nameTextView;
	private TextView _numberTextView;
	private Button _callButton;
	private Spinner _phoneSpinner;
	
	private Contact _selectedContact;
	private PhoneEntry _selectedPhoneEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_nameTextView = (TextView) findViewById(R.id.main_contact_name_text);
		_numberTextView = (TextView) findViewById(R.id.main_contact_number_text);
		_callButton = (Button) findViewById(R.id.call_button);
		_phoneSpinner = (Spinner) findViewById(R.id.main_phone_spinner);
		
		selectContact(null);
		
		_phoneSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				PhoneEntry entry = (PhoneEntry) parent.getItemAtPosition(position);
				selectPhoneEntry(entry);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				selectPhoneEntry(null);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onChooseContactClick(View view) {
		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, SELECT_CONTACT);
	}
	
	public void onCallClick(View view) {
		if (_selectedContact == null) {
			return;
		}
		
		Uri callUri = _selectedPhoneEntry.getPhoneNumber().prependWith("420").getCallUri();
		Intent intent = new Intent(Intent.ACTION_CALL, callUri);
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == SELECT_CONTACT && resultCode == Activity.RESULT_OK) {
			Uri contactUri = data.getData();
			Contact contact = new ContactLoader(this)
					.getContactByUri(contactUri);
			
			if (contact == null) {
				Toast.makeText(this, 
						getString(R.string.error_contact_loading), 
						Toast.LENGTH_LONG).show();
			}
			
			selectContact(contact);
		}
	}
	
	private void selectContact(Contact contact) {
		if (contact == null || contact.getPhoneEntries().isEmpty()) {
			_selectedContact = null;
			selectPhoneEntry(null);
			
			_nameTextView.setVisibility(View.INVISIBLE);
			_numberTextView.setVisibility(View.INVISIBLE);
			_callButton.setVisibility(View.INVISIBLE);
			_phoneSpinner.setVisibility(View.INVISIBLE);
		} else {
			_selectedContact = contact;
			selectPhoneEntry(contact.getPhoneEntries().get(0));
			
			_nameTextView.setVisibility(View.VISIBLE);
			_numberTextView.setVisibility(View.VISIBLE);
			_callButton.setVisibility(View.VISIBLE);
			_phoneSpinner.setVisibility(View.VISIBLE);
			
			_nameTextView.setText(contact.getName());
			
			_phoneSpinner.setAdapter(new ArrayAdapter<PhoneEntry>(this, android.R.layout.simple_spinner_dropdown_item, contact.getPhoneEntries()));
		}
	}
	
	private void selectPhoneEntry(PhoneEntry entry) {
		_selectedPhoneEntry = entry;
		if (entry != null) {
			_numberTextView.setText(_selectedPhoneEntry.getPhoneNumber().toString());
		}
	}
}
