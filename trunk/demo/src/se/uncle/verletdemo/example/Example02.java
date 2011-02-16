package se.uncle.verletdemo.example;

import java.util.List;

import se.uncle.verletdemo.VerletView;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class Example02 extends Activity {
	private VerletView view;
	private Paint red;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		red = new Paint();
		red.setColor(0xFFFF0000);
				
		view = new VerletView(this, new Cloth()) {
			@Override
			public void draw(Canvas canvas) {
				canvas.drawColor(0xFF000000);
				for ( int i = 0; i < system.m_constrainA.length; i++) {
					canvas.drawLine(system.m_x[system.m_constrainA[i]], system.m_y[system.m_constrainA[i]], system.m_x[system.m_constrainB[i]], system.m_y[system.m_constrainB[i]], red);
				}

				for ( int i = 0; i < system.m_x.length; i++) {
					 canvas.drawCircle(system.m_x[i], system.m_y[i], 3, paint);
				}
				super.draw(canvas);
			}
		};

		SensorManager sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		List<Sensor> liste = sManager.getSensorList(Sensor.TYPE_ALL);
		for (int a = 0; a < liste.size(); a++) {
			System.out.println("Sensor " + a + ": " + liste.get(a).getName());
		}
		sManager.registerListener(sensorEventListener, liste.get(0),
				sManager.SENSOR_DELAY_NORMAL);

		setContentView(view);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			float x = ev.getX();
			float y = ev.getY();
			view.setFixedPoint(x, y);
		}
		return false;
	}

	private SensorEventListener sensorEventListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent e) {
			synchronized (this) {
				view.setGravity(-e.values[0] / 5, e.values[1] / 5);
			}
		}

	};
}