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

import org.junit.jupiter.api.Test

import static groovy.test.GroovyAssert.shouldFail
import static org.quicktheories.QuickTheory.qt
import static org.quicktheories.generators.SourceDSL.integers

class MathSpec {
    @Test
    void "an int converted to a double should have no fractional part"() {
        qt().forAll(integers().all()).check { i ->
            def d = i.toDouble()
            Math.rint(d) == d
        }
    }

    @Test
    void "addition is commutative for integer"() {
        qt().forAll(integers().all(), integers().all())
                .check { a, b -> a + b == b + a }
    }

    @Test
    void "multiplication is commutative for integer"() {
        qt().forAll(integers().all(), integers().all())
                .check { a, b -> a * b == b * a }
    }

    @Test
    void "subtraction is not commutative for integer"() {
        def error = shouldFail(AssertionError) {
            qt().forAll(integers().all(), integers().all()).checkAssert { a, b ->
                assert a - b == b - a
            }
        }
        println error.message
    }

    @Test
    void "subtraction is not commutative example"() {
        assert 4 - 2 != 2 - 4
    }

    @Test
    void "subtraction is not commutative but is ignoring sign"() {
        qt().forAll(integers().all(), integers().all()).checkAssert { a, b ->
            (a - b).abs() == (b - a).abs()
        }
    }

    @Test
    void "subtraction and addition are inverse functions"() {
        qt().forAll(integers().all(), integers().all()).checkAssert { a, b ->
            a + b - b == a
        }
    }

    @Test
    void "addition is associative for integer"() {
        qt().forAll(integers().all(), integers().all(), integers().all()).check { a, b, c ->
            (a + b) + c == a + (b + c)
        }
    }

    @Test
    void "zero is the identity for integer"() {
        qt().forAll(integers().all()).checkAssert { i ->
            assert i + 0 == i
            assert 0 + i == i
        }
    }

    @Test
    void "subtracting any integer from itself gives zero"() {
        qt().forAll(integers().all()).checkAssert { i ->
            assert i - i == 0
        }
    }

    @Test
    void "abs is idempotent"() {
        qt().forAll(integers().all()).check { i ->
            i.abs() == i.abs().abs()
        }
    }

    @Test
    void "times two is the same as add to self"() {
        qt().forAll(integers().all()).check { i ->
            i * 2 == i + i
        }
    }

    @Test
    void "pow two is the same as multiply to self"() {
        qt().forAll(integers().between(0, 40_000)).check { i ->
            i**2 == i * i
        }
    }
}
