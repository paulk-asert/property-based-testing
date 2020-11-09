/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//@Grab('net.java.quickcheck:quickcheck:0.6')
import org.junit.Test

import static java.lang.Math.round
import static net.java.quickcheck.generator.PrimitiveGenerators.*
import static Converter.celsius

class CelsiusTest {
    @Test
    void testOrderingOfTwoValues() {
        def gen1 = integers(-40, 240)
        def gen2 = integers(-40, 240)
        100.times {
            int f1 = gen1.next()
            int f2 = gen2.next()
            def c1 = celsius(f1)
            def c2 = celsius(f2)
            assert (c1 <=> c2) == (f1 <=> f2)      // Prop 2
        }
    }

    @Test
    void testOrderingOfInputToOutputAndPhases() {
        def gen = integers(-40, 240)
        def liquidC =  0..100
        def liquidF = 32..212
        100.times {
            int f = gen.next()
            int c = round(celsius(f))
            assert c <= f                          // Prop 3
            assert c in liquidC == f in liquidF    // Prop 1
        }
    }
}
