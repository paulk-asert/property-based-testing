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

import org.junit.Test

import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.anyInteger

class MathSpec {
    @Test
    void "an int converted to a double should have no fractional part"() {
        10.times {
            def i = anyInteger()
            def d = i.toDouble()
            assert Math.rint(d) == d
        }
    }

    @Test
    void "addition is commutative for integer"() {
        10.times {
            def a = anyInteger()
            def b = anyInteger()
            assert a + b == b + a
        }
    }

    @Test
    void "multiplication is commutative for integer"() {
        10.times {
            def a = anyInteger()
            def b = anyInteger()
            assert a * b == b * a
        }
    }

    @Test
    void "subtraction is not commutative for integer"() {
        assert (1..10).any {
            def a = anyInteger()
            def b = anyInteger()
            a - b != b - a
        }
    }

    @Test
    void "subtraction is not commutative example"() {
        assert 4 - 2 != 2 - 4
    }

    @Test
    void "subtraction is not commutative but is ignoring sign"() {
        10.times {
            def a = anyInteger()
            def b = anyInteger()
            assert (a - b).abs() == (b - a).abs()
        }
    }

    @Test
    void "subtraction and addition are inverse functions"() {
        10.times {
            def a = anyInteger()
            def b = anyInteger()
            assert a + b - b == a
        }
    }

    @Test
    void "addition is associative for integer"() {
        10.times {
            def a = anyInteger()
            def b = anyInteger()
            def c = anyInteger()
            assert (a + b) + c == a + (b + c)
        }
    }

    @Test
    void "zero is the identity for integer"() {
        10.times {
            def i = anyInteger()
            assert i + 0 == i
            assert 0 + i == i
        }
    }

    @Test
    void "subtracting any integer from itself gives zero"() {
        10.times {
            def i = anyInteger()
            assert i - i == 0
        }
    }

    @Test
    void "abs is idempotent"() {
        10.times {
            def i = anyInteger()
            assert i.abs() == i.abs().abs()
        }
    }

    @Test
    void "times two is the same as add to self"() {
        10.times {
            def i = anyInteger()
            assert i * 2 == i + i
        }
    }

    @Test
    void "pow two is the same as multiply to self"() {
        10.times {
            def i = anyInteger(0, 40000) // avoid overflow
            assert  i * i == i ** 2
        }
    }
}
