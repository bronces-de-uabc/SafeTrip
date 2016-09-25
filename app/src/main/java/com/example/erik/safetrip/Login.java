package com.example.erik.safetrip;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onegravity.contactpicker.contact.Contact;
import com.onegravity.contactpicker.contact.ContactDescription;
import com.onegravity.contactpicker.contact.ContactSortOrder;
import com.onegravity.contactpicker.core.ContactPickerActivity;
import com.onegravity.contactpicker.group.Group;
import com.onegravity.contactpicker.picture.ContactPictureType;

import java.util.List;

public class Login extends AppCompatActivity {
    TextView correoLogin;
    TextView passwordLogin;
    Button enviaLogin;
    private boolean mDarkTheme;
    private static final int REQUEST_CONTACT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enviaLogin = (Button)findViewById(R.id.botonEnviar);
    }



    public void onEnvia(){
        correoLogin = (TextView)findViewById(R.id.correoUsuario);
        passwordLogin = (TextView)findViewById(R.id.password0);
       Intent intent = new Intent(this, ContactPickerActivity.class)
               .putExtra(ContactPickerActivity.EXTRA_THEME, mDarkTheme ? R.style.AppTheme : R.style.ContactPicker_Theme_Dark)
               .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE, ContactPictureType.ROUND.name())
               .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION, ContactDescription.ADDRESS.name())
               .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
               .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER, ContactSortOrder.AUTOMATIC.name());
        startActivityForResult(intent, REQUEST_CONTACT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK &&
                data != null && data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA)) {

            // we got a result from the contact picker

            // process contacts
            List<Contact> contacts = (List<Contact>) data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA);
            for (Contact contact : contacts) {
                // process the contacts...

            }

            // process groups
            List<Group> groups = (List<Group>) data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA);
            for (Group group : groups) {
                // process the groups...

            }
        }
    }
}
