package in.ac.srmuniv.externalstorage;

import in.ac.srmuniv.externalstorage.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StorageAndroidActivity extends Activity {
	
		private static final String TAG = "Dir";
		EditText txtData;
		Button btnWriteSDFile;
		Button btnReadSDFile;
		Button btnClearScreen;
		Button btnClose;


		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			// bind GUI elements with local controls
			txtData = (EditText) findViewById(R.id.txtData);
			txtData.setHint("Enter some lines of data here...");

			btnWriteSDFile = (Button) findViewById(R.id.btnWriteSDFile);
			btnWriteSDFile.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// write on SD card file data in the text box
				try {
					File myFile = new File(Environment.getExternalStorageDirectory().getPath()+"/navin.txt");
					myFile.createNewFile();
					FileOutputStream fOut = new FileOutputStream(myFile);
					OutputStreamWriter myOutWriter = 
											new OutputStreamWriter(fOut);
					myOutWriter.append(txtData.getText());
					myOutWriter.close();
					 fOut.close();
					Toast.makeText(getBaseContext(),
							"Done writing SD 'navin.txt'",
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(getBaseContext(), e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
			}// onClick
			}); // btnWriteSDFile

				btnReadSDFile = (Button) findViewById(R.id.btnReadSDFile);
				btnReadSDFile.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// write on SD card file data in the text box
				try {
					File myFile = new File("/sdcard/navin.txt");
					FileInputStream fIn = new FileInputStream(myFile);
					BufferedReader myReader = new BufferedReader(
							new InputStreamReader(fIn));
					String aDataRow = "";
					String aBuffer = "";
					while ((aDataRow = myReader.readLine()) != null) {
						aBuffer += aDataRow + "\n";
					}
					txtData.setText(aBuffer);
					myReader.close();
					Toast.makeText(getBaseContext(),
							"Done reading SD 'navin.txt'",
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(getBaseContext(), e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
				}// onClick
				}); // btnReadSDFile

				btnClearScreen = (Button) findViewById(R.id.btnClearScreen);
				btnClearScreen.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// clear text box
						txtData.setText("");
					}
				}); // btnClearScreen

				btnClose = (Button) findViewById(R.id.btnClose);
				btnClose.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// clear text box
						finish();
					}
				}); // btnClose

			File sddir = new File("/sdcard/SRMDirectory");  
			  
	        if (sddir.mkdirs()) {  
	             Toast toast = Toast.makeText(this,  
	             "Directory successfully created!",  
	                    Toast.LENGTH_SHORT);  
	         toast.setGravity(Gravity.CENTER, 0, 0);  
	         toast.show();  
	        }else{  
	             Log.e(TAG, "Create dir in sdcard failed");  
	             Toast toast = Toast.makeText(this,  
	             "Directory creation failed!",  
	                    Toast.LENGTH_SHORT);  
	         toast.setGravity(Gravity.CENTER, 0, 0);  
	         toast.show();  
	        }  
			

		}

}
