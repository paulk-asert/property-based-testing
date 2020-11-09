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

import fj.F2
import fj.F3
import fj.test.Property
import fj.test.runner.PropertyTestRunner
import org.junit.runner.RunWith

import static fj.test.Arbitrary.arbInteger
import static fj.test.Property.implies
import static fj.test.Property.prop
import static fj.test.Property.property

@RunWith(PropertyTestRunner)
class MathSpec {
    Property "an int converted to a double should have no fractional part"() {
        property(arbInteger, { Integer i ->
            def d = i.toDouble()
            prop(Math.rint(d) == d)
        })
    }

    Property "addition is commutative for integer"() {
        F2 prop = { int a, int b -> prop(a + b == b + a) }
        property(arbInteger, arbInteger, prop)
    }

    Property "multiplication is commutative for integer"() {
        F2 prop = { int a, int b -> prop(a * b == b * a) }
        property(arbInteger, arbInteger, prop)
    }

    // expect some values to be discarded
    Property "subtraction is not commutative for integer"() {
        F2 prop = { int a, int b -> implies(a != b, () -> prop(a - b != b - a)) }
        property(arbInteger, arbInteger, prop)
    }

    Property "subtraction is not commutative but is ignoring sign"() {
        F2 prop = { int a, int b -> prop((a - b).abs() == (b - a).abs()) }
        property(arbInteger, arbInteger, prop)
    }

    Property "subtraction and addition are inverse functions"() {
        F2 prop = { int a, int b -> prop(a + b - b == a) }
        property(arbInteger, arbInteger, prop)
    }

    Property "addition is associative for integer"() {
        F3 prop = { int a, int b, int c -> prop((a + b) + c == a + (b + c)) }
        property(arbInteger, arbInteger, arbInteger, prop)
    }

    Property "zero is the identity for integer"() {
        property(arbInteger, { Integer i -> prop(i + 0 == i && 0 + i == i) })
    }

    Property "subtracting any integer from itself gives zero"() {
        property(arbInteger, { Integer i -> prop(i - i == 0) })
    }

    Property "abs is idempotent"() {
        property(arbInteger, { Integer i -> prop(i.abs() == i.abs().abs()) })
    }

    Property "times two is the same as add to self"() {
        property(arbInteger, { Integer i -> prop(i * 2 == i + i) })
    }

    // default integers are smallish, so no overflow expected
    Property "pow two is the same as multiply to self"() {
        property(arbInteger, { Integer i -> prop(i**2 == i * i) })
    }
}
