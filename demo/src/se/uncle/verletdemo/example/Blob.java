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

public class Blob extends VerletIntegrationSystem {

	@Override
	protected void initilaze() {
		NUM_PARTICLES = 9;
		NUM_CONSTRAINTS = 20; // (NUM_PARTICLES - 1) / 2 * 5;
		NUM_ITERATIONS = 20;
		restlength = 50f;
		
		m_x = new float[NUM_PARTICLES]; // Current x position.
		m_y = new float[NUM_PARTICLES]; // Current y position.

		m_oldx = new float[NUM_PARTICLES]; // Previous x position.
		m_oldy = new float[NUM_PARTICLES]; // Previous y position.

		m_ax = new float[NUM_PARTICLES]; // Force accumulators
		m_ay = new float[NUM_PARTICLES]; // Force accumulators

		m_stuck = new boolean[NUM_PARTICLES];

		float dist = restlength / 2;
		
		m_x[0] = 240;
		m_y[0] = 400;
		
		m_x[1] = 240;
		m_y[1] = 400 - dist;
		
		m_x[2] = 240;
		m_y[2] = 400 - dist *2;
		
		m_x[3] = 245 + dist;
		m_y[3] = 400;
		
		m_x[4] = 250 + 2 * dist;
		m_y[4] = 400;
		
		m_x[5] = 240;
		m_y[5] = 400 + dist;
		
		m_x[6] = 240;
		m_y[6] = 410 + 2 * dist;
		
		m_x[7] = 240 - dist;
		m_y[7] = 400;
		
		m_x[8] = 230 - 2 * dist;
		m_y[8] = 400;
		
		for (int i = 0; i < NUM_PARTICLES; i++) {
//			m_x[i] = 240;
//			m_y[i] = 400;

			m_oldx[i] = m_x[i]; // 240;
			m_oldy[i] = m_y[i]; //400;

			m_ax[i] = 0;
			m_ay[i] = 0;
		}

		m_constrainA = new int[NUM_CONSTRAINTS];
		m_constrainB = new int[NUM_CONSTRAINTS];
		m_constrainD = new float[NUM_CONSTRAINTS];
		
		m_constrainA[0] = 0;
		m_constrainB[0] = 1;
		m_constrainD[0] = dist;
		m_constrainA[1] = 1;
		m_constrainB[1] = 2;
		m_constrainD[1] = dist;
		m_constrainA[2] = 1;
		m_constrainB[2] = 3;
		m_constrainD[2] = dist;
		m_constrainA[3] = 2;
		m_constrainB[3] = 3;
		m_constrainD[3] = dist;
		m_constrainA[4] = 4;
		m_constrainB[4] = 1;
		m_constrainD[4] = dist;
		
		m_constrainA[5] = 0;
		m_constrainB[5] = 3;
		m_constrainD[5] = dist;
		m_constrainA[6] = 3;
		m_constrainB[6] = 4;
		m_constrainD[6] = dist;
		m_constrainA[7] = 3;
		m_constrainB[7] = 5;
		m_constrainD[7] = dist;
		m_constrainA[8] = 4;
		m_constrainB[8] = 5;
		m_constrainD[8] = dist;
		m_constrainA[9] = 6;
		m_constrainB[9] = 3;
		m_constrainD[9] = dist;
		
		m_constrainA[10] = 0;
		m_constrainB[10] = 5;
		m_constrainD[10] = dist;
		m_constrainA[11] = 5;
		m_constrainB[11] = 6;
		m_constrainD[11] = dist;
		m_constrainA[12] = 5;
		m_constrainB[12] = 7;
		m_constrainD[12] = dist;
		m_constrainA[13] = 6;
		m_constrainB[13] = 7;
		m_constrainD[13] = dist;
		m_constrainA[14] = 8;
		m_constrainB[14] = 5;
		m_constrainD[14] = dist;
		
		m_constrainA[15] = 0;
		m_constrainB[15] = 7;
		m_constrainD[15] = dist;
		m_constrainA[16] = 7;
		m_constrainB[16] = 8;
		m_constrainD[16] = dist;
		m_constrainA[17] = 7;
		m_constrainB[17] = 9;
		m_constrainD[17] = dist;
		m_constrainA[18] = 8;
		m_constrainB[18] = 1;
		m_constrainD[18] = dist;
		m_constrainA[19] = 2;
		m_constrainB[19] = 7;
		m_constrainD[19] = dist;
//		int index = 0;
//		for (int n = 1; n < NUM_PARTICLES; n += 2) {
//			if (n < NUM_PARTICLES - 3) {
//				m_constrainA[index] = 0;
//				m_constrainB[index] = n;
//				index++;
//				m_constrainA[index] = n;
//				m_constrainB[index] = n + 1;
//				index++;
//				m_constrainA[index] = n;
//				m_constrainB[index] = n + 2;
//				index++;
//				m_constrainA[index] = n + 1;
//				m_constrainB[index] = n + 3;
//				index++;
//				m_constrainA[index] = n + 2;
//				m_constrainB[index] = n + 1;
//				index++;
//			} else {
//				m_constrainA[index] = 0;
//				m_constrainB[index] = n;
//				index++;
//				m_constrainA[index] = n;
//				m_constrainB[index] = n + 1;
//				index++;
//				m_constrainA[index] = n;
//				m_constrainB[index] = 1;
//				index++;
//				m_constrainA[index] = n + 1;
//				m_constrainB[index] = 2;
//				index++;
//				m_constrainA[index] = 2;
//				m_constrainB[index] = n + 1;
//				index++;
//			}
//		}

	}

	@Override
	public void setFixed() {
		// for ( int k = 0; k < 10; k+=3) {
		m_x[0] = 240;
		m_y[0] = 400;
		// }
	}

}
