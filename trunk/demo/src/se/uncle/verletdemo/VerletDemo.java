package se.uncle.verletdemo;

import se.uncle.verletdemo.example.Example01;
import se.uncle.verletdemo.example.Example02;
import se.uncle.verletdemo.example.Example03;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VerletDemo extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		String[] examples = new String[] { "Rope", "Cloth", "Cube" }; // ,
																		// "Blob"
																		// };
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, examples));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent;
		switch (position) {
		default:
		case 0: // Rope
			intent = new Intent(this, Example01.class);
			startActivity(intent);
			break;
		case 1: // Cloth
			intent = new Intent(this, Example02.class);
			startActivity(intent);
			break;
		case 2: // Blob
			intent = new Intent(this, Example03.class);
			startActivity(intent);
			break;
		}
	}
}