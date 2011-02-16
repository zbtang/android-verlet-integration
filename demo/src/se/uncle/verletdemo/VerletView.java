package se.uncle.verletdemo;

import se.uncle.verlet.VerletIntegrationSystem;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VerletView extends View {

	protected VerletIntegrationSystem system;
	protected Paint paint;
	private long timepassed = 0;
	private int framecounter = 0;
	private double r;

	private Bitmap bitmap;
	
	public VerletView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public VerletView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public VerletView(Context context, VerletIntegrationSystem system) {
		super(context);
		float[] x = new float[]{240, 240, 240, 240};
		float[] y = new float[]{120, 120, 120, 120};
		this.system = system;
		paint = new Paint();
		paint.setColor(0xFFFFFFFF);
		timepassed = System.currentTimeMillis();
	}

	public void setGravity(float x, float y) {
		system.setGravity(x, y);
	}

	public void setFixedPoint(float pX, float pY) {
		system.setFixedPoint(pX, pY, 0);
	}

	@Override
	public void draw(Canvas canvas) {
		paint.setColor(0xFFFFFFFF);
		framecounter++;
		if (System.currentTimeMillis() - timepassed > 1000) {
			long v = System.currentTimeMillis() - timepassed;
			r = ((double) framecounter / (double) v) * 1000;
		}
		canvas.drawText("FPS:" + r, 10, 15, paint);
		system.timeStep();
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		invalidate();
	}
}
