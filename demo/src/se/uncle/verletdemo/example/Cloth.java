package se.uncle.verletdemo.example;

import se.uncle.verlet.VerletIntegrationSystem;

public class Cloth extends VerletIntegrationSystem {
	int width;
	int height;

	@Override
	protected void initilaze() {
		width = 10;
		height = 10;
		
		NUM_PARTICLES = width * height; // numOfParticles;
		restlength = 400 / (width - 1);
		m_x = new float[NUM_PARTICLES]; // Current x position.
		m_y = new float[NUM_PARTICLES]; // Current y position.

		m_oldx = new float[NUM_PARTICLES]; // Previous x position.
		m_oldy = new float[NUM_PARTICLES]; // Previous y position.

		m_ax = new float[NUM_PARTICLES]; // Force accumulators
		m_ay = new float[NUM_PARTICLES]; // Force accumulators

		m_stuck = new boolean[NUM_PARTICLES];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int index = (y * width) + x;
				m_x[index] = x * restlength;
				m_y[index] = y * restlength;
				m_oldx[index] = x * restlength;
				m_oldy[index] = y * restlength;
			}
		}

		NUM_CONSTRAINTS = (width * (height - 1)) + ((width - 1) * height);

		m_constrainA = new int[NUM_CONSTRAINTS];
		m_constrainB = new int[NUM_CONSTRAINTS];
		m_constrainD = new float[NUM_CONSTRAINTS];

		int index = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width - 1; x++) {
				m_constrainA[index] = (y * width) + x;
				m_constrainB[index] = (y * width) + x + 1;
				m_constrainD[index] = restlength;
				index++;
			}
		}

		for (int y = 0; y < height - 1; y++) {
			for (int x = 0; x < width; x++) {
				m_constrainA[index] = (y * width) + x;
				m_constrainB[index] = (y * width) + x + width;
				m_constrainD[index] = restlength;
				index++;
			}
		}
	}

	@Override
	public void setFixed() {
		
		for (int k = 0; k < width; k+=3) {
			m_y[k] = 40;
			m_x[k] = k * restlength + 40;
		}
	}

}
