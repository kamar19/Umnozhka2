package com.firstSet.MultiplyIt;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.firstSet.umnozhka.R;

import static android.app.PendingIntent.getActivity;

//public class PrefActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public class PrefActivity extends PreferenceActivity {
//      private static final String PREFERENCES_SETTINGS_NAME = "umnozhka_Settings";
//     private static SharedPreferences sharedPreferences2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PreferenceManager.setDefaultValues(getActivity(),
//                R.xml.pref, false);
        addPreferencesFromResource(R.xml.pref);
//        sharedPreferences2 =  getSharedPreferences(PREFERENCES_SETTINGS_NAME, MODE_PRIVATE);

    }

//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
////        if (key.equals("umnozhka_Settings"))
////        {
////
////            // get preference by key
////            Preference pref = findPreference(key);
////            // do your stuff here
////        }
//        Intent intent = getIntent();
//        finish();
//        startActivity(intent);
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Set up a listener whenever a key changes
//        getPreferenceScreen().getSharedPreferences()
//                .registerOnSharedPreferenceChangeListener(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Unregister the listener whenever a key changes
//        getPreferenceScreen().getSharedPreferences()
//                .unregisterOnSharedPreferenceChangeListener(this);
//    }

}

