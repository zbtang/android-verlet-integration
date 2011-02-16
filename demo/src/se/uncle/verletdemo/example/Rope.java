/**
 * Copyright 2010 Per-Erik Bergman (bergman@uncle.se)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.uncle.verletdemo.example;

import se.uncle.verlet.VerletIntegrationSystem;

public class Rope extends VerletIntegrationSystem {

	@Override
	protected void initilaze() {
		NUM_PARTICLES = 30;
		NUM_CONSTRAINTS = NUM_PARTICLES - 1;
		NUM_ITERATIONS = 10;
		restlength = 10f;
		
		m_x = new float[NUM_PARTICLES]; // Current x position.
		m_y = new float[NUM_PARTICLES]; // Current y position.

		m_oldx = new float[NUM_PARTICLES]; // Previous x position.
		m_oldy = new float[NUM_PARTICLES]; // Previous y position.

		m_ax = new float[NUM_PARTICLES]; // Force accumulators
		m_ay = new float[NUM_PARTICLES]; // Force accumulators

		for (int i = 0; i < NUM_PARTICLES; i++) {
			m_x[i] = 240;
			m_y[i] = 0 + i * 3;

			m_oldx[i] = 240;
			m_oldy[i] = 0 + i * 3;

			m_ax[i] = 0;
			m_ay[i] = 0;
			
		}

		m_constrainA = new int[NUM_CONSTRAINTS];
		m_constrainB = new int[NUM_CONSTRAINTS];
		m_constrainD = new float[NUM_CONSTRAINTS];
		int index = 0;
		for (int p = 0; p < NUM_PARTICLES-1; p++) {
			m_constrainA[index] = p;
			m_constrainB[index] = p + 1;
			m_constrainD[index] = restlength;
			index++;
		}
	}

	@Override
	public void setFixed() {
		m_x[0] = 240;
		m_y[0] = 100;
	}

}
