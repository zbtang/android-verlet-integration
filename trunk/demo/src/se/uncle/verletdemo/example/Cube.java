package se.uncle.verletdemo.example;

import se.uncle.verlet.VerletIntegrationSystem;

public class Cube extends VerletIntegrationSystem {

	@Override
	protected void initilaze() {
		NUM_PARTICLES = 4;
		NUM_CONSTRAINTS = 6; // (NUM_PARTICLES - 1) / 2 * 5;
		NUM_ITERATIONS = 5;
		restlength = 100f;

		m_x = new float[NUM_PARTICLES]; // Current x position.
		m_y = new float[NUM_PARTICLES]; // Current y position.

		m_oldx = new float[NUM_PARTICLES]; // Previous x position.
		m_oldy = new float[NUM_PARTICLES]; // Previous y position.

		m_ax = new float[NUM_PARTICLES]; // Force accumulators
		m_ay = new float[NUM_PARTICLES]; // Force accumulators

		m_stuck = new boolean[NUM_PARTICLES];

		float dist = restlength / 2;

		m_x[0] = 240 - 2 * dist;
		m_y[0] = 400 - dist;

		m_x[1] = 240 + dist;
		m_y[1] = 400 - dist;

		m_x[2] = 240 + dist;
		m_y[2] = 400 + dist;

		m_x[3] = 245 - dist;
		m_y[3] = 400 + dist;

		for (int i = 0; i < NUM_PARTICLES; i++) {
			m_oldx[i] = m_x[i];
			m_oldy[i] = m_y[i];
			m_ax[i] = 0;
			m_ay[i] = 0;
		
		}

		
		m_constrainA = new int[NUM_CONSTRAINTS];
		m_constrainB = new int[NUM_CONSTRAINTS];
		m_constrainD = new float[NUM_CONSTRAINTS];

		m_constrainA[0] = 0;
		m_constrainB[0] = 1;
		m_constrainD[0] = restlength;
		m_constrainA[1] = 1;
		m_constrainB[1] = 2;
		m_constrainD[1] = restlength;
		m_constrainA[2] = 2;
		m_constrainB[2] = 3;
		m_constrainD[2] = restlength;
		m_constrainA[3] = 3;
		m_constrainB[3] = 0;
		m_constrainD[3] = restlength;
		m_constrainA[4] = 0;
		m_constrainB[4] = 2;
		m_constrainD[4] = (float) (Math.sqrt(restlength * restlength + restlength * restlength));
		m_constrainA[5] = 1;
		m_constrainB[5] = 3;
		m_constrainD[5] = (float) (Math.sqrt(restlength * restlength + restlength * restlength));
	}

	@Override
	public void setFixed() {
		// for ( int k = 0; k < 10; k+=3) {
//		m_x[0] = f_x;
//		m_y[0] = f_y;
		// }
	}

}
