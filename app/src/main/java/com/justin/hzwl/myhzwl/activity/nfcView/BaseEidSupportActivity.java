package com.justin.hzwl.myhzwl.activity.nfcView;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.provider.Settings;

/**
 * Created by KING on 2014/10/30.
 */
@SuppressLint("NewApi")
public abstract class BaseEidSupportActivity extends android.app.Activity {
	protected final String TAG = getClass().getSimpleName();
	public long ret = -1;

	// NFC parts
	protected static NfcAdapter mAdapter;
	private static PendingIntent mPendingIntent;
	private static IntentFilter[] mFilters;
	private static String[][] mTechLists;
	protected Resources res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		res = getResources();
		initNfc();
	}
	@Override
	protected void onPause() {
		super.onPause();
		if (mAdapter != null && mAdapter.isEnabled())
			mAdapter.disableForegroundDispatch(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mAdapter != null)
			mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
					mTechLists);
	}
	
	@SuppressLint("NewApi")
	private void initNfc() {
		
		mAdapter = NfcAdapter.getDefaultAdapter(this);
		if (mAdapter == null) {
			return;
		}
		
		mPendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this.getApplicationContext(), getClass())
						.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		IntentFilter tech = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		IntentFilter tag = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		try {

			ndef.addDataType("*/*");

		} catch (MalformedMimeTypeException e) {

			throw new RuntimeException("fail", e);

		}
		mFilters = new IntentFilter[] { tech, ndef, tag };

		// Setup a teach list for all NfcF tags
		mTechLists = new String[][] { new String[] { IsoDep.class.getName(),
				NfcA.class.getName(),NfcB.class.getName(),NfcF.class.getName(),NfcV.class.getName()} };

	}
	@Override
	protected abstract void onNewIntent(Intent intent);
	
	@SuppressLint("InlinedApi")
    protected void openNFCSettings() {

        Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        
    }
}
