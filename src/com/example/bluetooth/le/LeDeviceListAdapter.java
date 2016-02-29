package com.example.bluetooth.le;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bluetooth.le.iBeaconClass.iBeacon;

public class LeDeviceListAdapter extends BaseAdapter {

	// Adapter for holding devices found through scanning.

	private ArrayList<iBeacon> mLeDevices;
	private LayoutInflater mInflator;
	private Activity mContext;

	public LeDeviceListAdapter(Activity c) {
		super();
		mContext = c;
		mLeDevices = new ArrayList<iBeacon>();
		mInflator = mContext.getLayoutInflater();
	}

	public void addDevice(iBeacon device) {
		if(device==null)
			return;
		
		for(int i=0;i<mLeDevices.size();i++){
			String btAddress = mLeDevices.get(i).bluetoothAddress;
			if(btAddress.equals(device.bluetoothAddress)){
				mLeDevices.add(i+1, device);
				mLeDevices.remove(i);
				return;
			}
		}
		mLeDevices.add(device);
		
	}

	public iBeacon getDevice(int position) {
		return mLeDevices.get(position);
	}

	public void clear() {
		mLeDevices.clear();
	}

	@Override
	public int getCount() {
		return mLeDevices.size();
	}

	@Override
	public Object getItem(int i) {
		return mLeDevices.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder viewHolder;
		// General ListView optimization code.
		if (view == null) {
			view = mInflator.inflate(R.layout.listitem_device, null);
			viewHolder = new ViewHolder();
			viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
			viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
//			viewHolder.deviceUUID= (TextView)view.findViewById(R.id.device_beacon_uuid);
			viewHolder.deviceMajor_Minor=(TextView)view.findViewById(R.id.device_major_minor);
//			viewHolder.devicetxPower_RSSI=(TextView)view.findViewById(R.id.device_txPower_rssi);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		iBeacon device = mLeDevices.get(i);
		final String deviceName = device.name;
		if (deviceName != null && deviceName.length() > 0)
			viewHolder.deviceName.setText(deviceName);
		else
			viewHolder.deviceName.setText(R.string.unknown_device);
		
		viewHolder.deviceAddress.setText("MAC: "+device.bluetoothAddress);
//		viewHolder.deviceUUID.setText("UUID: "+device.proximityUuid);
		viewHolder.deviceMajor_Minor.setText("Major: "+device.major+"   Minor: "+device.minor);
//		viewHolder.devicetxPower_RSSI.setText("Power: "+device.txPower+",Rssi: "+device.rssi);
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent display = new Intent(mContext,DisplayActivity.class);
				mContext.startActivity(display);
			}
		});
		
		return view;
	}

	class ViewHolder {
		TextView deviceName;
		TextView deviceAddress;
		TextView deviceUUID;
		TextView deviceMajor_Minor;
		TextView devicetxPower_RSSI;
	}
}
