package se.uncle.verlet;


public abstract class VerletIntegrationSystem {
	public static int NUM_PARTICLES = 0;
	public static int NUM_CONSTRAINTS = 0;

	public float[] m_x = new float[NUM_PARTICLES]; // Current x position.
	public float[] m_y = new float[NUM_PARTICLES]; // Current y position.

	public float[] m_oldx = new float[NUM_PARTICLES]; // Previous x position.
	public float[] m_oldy = new float[NUM_PARTICLES]; // Previous y position.

	public float[] m_ax = new float[NUM_PARTICLES]; // Force accumulators
	public float[] m_ay = new float[NUM_PARTICLES]; // Force accumulators

	public boolean[] m_stuck = new boolean[NUM_PARTICLES];

	public int[] m_constrainA = new int[NUM_CONSTRAINTS];
	public int[] m_constrainB = new int[NUM_CONSTRAINTS];
	public float[] m_constrainD = new float[NUM_CONSTRAINTS];
	
	public float m_vGravityx = 0; // Gravity
	public float m_vGravityy = 0.5f; // Gravity

	public float m_fTimeStep = 1.0f;

	public static int NUM_ITERATIONS = 20;

	public float restlength = 20f;

	public float f_x = 240;

	public float f_y = 240;

	public VerletIntegrationSystem() {
		initilaze();
	}

	protected abstract void initilaze();

	public void updateGravity(float p_x, float p_y, float p_z) {
		m_vGravityx = p_x;
		m_vGravityy = p_y;
	}

	public void setFixedPoint(float p_x, float p_y, int point) {
		f_x = p_x;
		f_y = p_y;
		// m_x[point] = p_x;
		// m_y[point] = p_y;
		// m_stuck[point] = true;
	}

	public void timeStep() {
		accumulateForces();
		verlet();
		satisfyConstraints();
	}

	// Verlet integration step
	private void verlet() {
		for (int i = 0; i < NUM_PARTICLES; i++) {
			float x = m_x[i];
			float temp_x = m_x[i];
			float oldx = m_oldx[i];
			float ax = m_ax[i];
			m_x[i] += m_x[i] - oldx + ax * m_fTimeStep * m_fTimeStep; // x
			m_oldx[i] = temp_x;

			float y = m_y[i];
			float temp_y = m_y[i];
			float oldy = m_oldy[i];
			float ay = m_ay[i];
			m_y[i] += m_y[i] - oldy + ay * m_fTimeStep * m_fTimeStep;
			m_oldy[i] = temp_y;
		}
	}

	public void setGravity(float x, float y) {
		m_vGravityx = x;
		m_vGravityy = y;
	}

	// This function should accumulate forces for each particle
	private void accumulateForces() {
		// All particles are influenced by gravity
		for (int i = 0; i < NUM_PARTICLES; i++) {
			m_ax[i] = m_vGravityx;
			m_ay[i] = m_vGravityy;
		}
	}

	// Here constraints should be satisfied
	protected void satisfyConstraints() {
		for (int j = 0; j < NUM_ITERATIONS; j++) {
			// First satisfy (C1)
			for (int i = 0; i < NUM_PARTICLES; i++) { // For all particles
				if (m_x[i] < 0) {
					m_x[i] = 0;
				} else if (m_x[i] > 480) {
					m_x[i] = 480;
				}

				if (m_y[i] < 0) {
					m_y[i] = 0;
				} else if (m_y[i] > 800) {
					m_y[i] = 800;
				}
			}

			for (int i = 0; i < NUM_CONSTRAINTS; i++) {
				int a = m_constrainA[i];
				int b = m_constrainB[i];
				
				float deltaX = m_x[a] - m_x[b];
				float deltaY = m_y[a] - m_y[b];

				float deltalength = (float) Math.sqrt(deltaX * deltaX + deltaY
						* deltaY);
				float diff = (deltalength - m_constrainD[i]) / deltalength;

				m_x[b] += deltaX * 0.5 * diff;
				m_x[a] -= deltaX * 0.5 * diff;
				m_y[b] += deltaY * 0.5 * diff;
				m_y[a] -= deltaY * 0.5 * diff;
			}
			setFixed();

		}
	}
	
	public abstract void setFixed();
}
