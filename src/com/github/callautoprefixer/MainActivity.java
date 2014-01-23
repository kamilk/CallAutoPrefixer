package com.github.callautoprefixer;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	final static int SELECT_CONTACT = 101;
	
	private TextView _nameTextView;
	private TextView _numberTextView;
	private Button _callButton;
	private Spinner _phoneSpinner;
	
	private Contact _selectedContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_nameTextView = (TextView) findViewById(R.id.main_contact_name_text);
		_numberTextView = (TextView) findViewById(R.id.main_contact_number_text);
		_callButton = (Button) findViewById(R.id.call_button);
		_phoneSpinner = (Spinner) findViewById(R.id.main_phone_spinner);
		
		selectContact(null);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[] { "Home", "Mobile" });
		_phoneSpinner.setAdapter(arrayAdapter);
		
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
		
		Intent intent = new Intent(Intent.ACTION_CALL, _selectedContact.getPhoneNumber().prependWith("420").getCallUri());
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == SELECT_CONTACT && resultCode == Activity.RESULT_OK) {
			Uri contactUri = data.getData();
			Contact contact = new ContactLoader(this)
					.getContactByUri(contactUri);
			
			selectContact(contact);
		}
	}
	
	private void selectContact(Contact contact) {
		_selectedContact = contact;
		
		if (contact == null) {
			_nameTextView.setVisibility(View.INVISIBLE);
			_numberTextView.setVisibility(View.INVISIBLE);
			_callButton.setVisibility(View.INVISIBLE);
		} else {
			_nameTextView.setVisibility(View.VISIBLE);
			_numberTextView.setVisibility(View.VISIBLE);
			_callButton.setVisibility(View.VISIBLE);
			
			_nameTextView.setText(contact.getName());
			_numberTextView.setText(contact.getPhoneNumber().toString());
		}
	}
}
