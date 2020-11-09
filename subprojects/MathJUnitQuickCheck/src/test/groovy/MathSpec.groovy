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

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.generator.InRange
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck
import org.junit.Test
import org.junit.runner.RunWith

import static com.pholser.junit.quickcheck.Mode.EXHAUSTIVE
import static groovy.test.GroovyAssert.shouldFail
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assume.assumeThat

@RunWith(JUnitQuickcheck)
class MathSpec {
    @Property
    void "an int converted to a double should have no fractional part"(Integer i) {
        10.times {
            def d = i.toDouble()
            assert Math.rint(d) == d
        }
    }

    @Property
    void "addition is commutative for integer"(Integer a, Integer b) {
        assert a + b == b + a
    }

    @Property
    void "multiplication is commutative for integer"(int a, int b) {
        assert a * b == b * a
    }

    @Property
    // in theory could fail if a == b but random generator good at avoiding that
    void "subtraction is not commutative for integer"(int a, int b) {
        assert a - b != b - a
    }

    @Property(trials = 250, mode = EXHAUSTIVE)
    void "subtraction is not commutative for integer with guard"(@InRange(minInt = 0, maxInt = 200) int a,
                                                                 @InRange(minInt = 0, maxInt = 200) int b) {
        assumeThat a, not(b)
        assert a - b != b - a
    }

    @Test
    void "subtraction is not commutative example"() {
        assert 4 - 2 != 2 - 4
    }

    @Test
    void "subtraction is not commutative counter-example"() {
        shouldFail(AssertionError) {
            assert 4 - 4 != 4 - 4
        }
    }

    @Property
    void "subtraction is not commutative but is ignoring sign"(int a, int b) {
        assert (a - b).abs() == (b - a).abs()
    }

    @Property
    void "subtraction and addition are inverse functions"(int a, int b) {
        assert a + b - b == a
    }

    @Property
    void "addition is associative for integer"(int a, int b, int c) {
        assert (a + b) + c == a + (b + c)
    }

    @Property
    void "zero is the identity for integer"(int i) {
        assert i + 0 == i
        assert 0 + i == i
    }

    @Property
    void "subtracting any integer from itself gives zero"(int i) {
        assert i - i == 0
    }

    @Property
    void "abs is idempotent"(int i) {
        assert i.abs() == i.abs().abs()
    }

    @Property
    void "times two is the same as add to self"(int i) {
        assert i * 2 == i + i
    }

    @Property
    void "pow two is the same as multiply to self"(@InRange(minInt = 0, maxInt = 40_000) int i) {
        assert i**2 == i * i
    }
}
