package com.xidstudios.android.utils.datecountdown;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class Display extends Activity implements OnClickListener {
	Button btnCheck;
	EditText etDay, etMonth, etYear;
	TextView display;

	DatePicker dPicker;

	Date now = null, d = null;

	long day;
	long hour;
	long minutes;
	long sec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);

		btnCheck = (Button) findViewById(R.id.btnCheck);
		btnCheck.setOnClickListener(this);

		display = (TextView) findViewById(R.id.dayTillDisplay);

		dPicker = (DatePicker) findViewById(R.id.datePicker1);
		// this.run();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btnCheck) {
			String day = "" + dPicker.getDayOfMonth(), mon = ""
					+ (dPicker.getMonth() + 1), yea = "" + dPicker.getYear();

			int lengthOfYear = yea.length();

			if (lengthOfYear < 4) {
				yea = "20" + yea;
			}

			String date = yea + "/" + mon + "/" + day;

			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			now = new Date();
			if (date != null) {
				try {
					d = parser.parse(date);

					if (d.after(now)) {
						updateCountDown(now, d);

					} else if (d.equals(now)) {
						display.setText("This Date is Today");
					} else if (d.before(now)) {
						display.setText("This Date Has Passed!!");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void updateCountDown(Date t, Date e) {
		final Date d1 = t;
		final Date d2 = e;

		long date = d2.getTime() - d1.getTime();

		day = date / (24 * 3600 * 1000);
		hour = (date % (24 * 3600 * 1000)) / (3600 * 1000);
		minutes = ((date % (24 * 3600 * 1000)) % (3600 * 1000) / (60 * 1000));
		sec = ((date % (24 * 3600 * 1000)) % (3600 * 1000) % (60 * 1000)) / (1000);

		display.setText("Days: " + day + ", Hours: " + hour + ", Minutes: "
				+ minutes + ", Seconds: " + sec);
	}
}
