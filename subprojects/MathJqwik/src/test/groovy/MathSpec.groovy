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
import net.jqwik.api.Arbitraries
import net.jqwik.api.Disabled
import net.jqwik.api.ForAll
import net.jqwik.api.Example
import net.jqwik.api.Property

class MathSpec {
    @Property
    def "an int converted to a double should have no fractional part"(@ForAll Integer i) {
        def d = i.toDouble()
        assert Math.rint(d) == d
    }

    @Property
    def "addition is commutative for integer"(@ForAll Integer a, @ForAll Integer b) {
        assert a + b == b + a
    }

    @Property
    def "multiplication is commutative for integer"(@ForAll Integer a, @ForAll Integer b) {
        assert a * b == b * a
    }

    @Property
    @Disabled('enable to see counter-examples')
    def "subtraction is not commutative for integer"(@ForAll Integer a, @ForAll Integer b) {
        assert a - b == b - a
    }

    @Example
    boolean "subtraction is not commutative example"() {
        4 - 2 != 2 - 4
    }

    @Example
    void "subtraction is not commutative property"() {
        def r = new Random()
        def ita = Arbitraries.integers().generator(1)
        def itb = Arbitraries.integers().generator(2)
        assert (1..10).any {
            def a = ita.next(r).value()
            def b = itb.next(r).value()
            a - b != b - a
        }
    }

    @Property
    def "subtraction is not commutative but is ignoring sign"(@ForAll Integer a, @ForAll Integer b) {
        assert (a - b).abs() == (b - a).abs()
    }

    @Property
    def "addition is associative for integer"(@ForAll Integer a, @ForAll Integer b, @ForAll Integer c) {
        assert (a + b) + c == a + (b + c)
    }

    @Property
    def "zero is the identity for integer"(@ForAll Integer a) {
        assert a + 0 == a
        assert 0 + a == a
    }

    @Property
    def "subtracting any integer from itself gives zero"(@ForAll Integer a) {
        assert a - a == 0
    }
}
