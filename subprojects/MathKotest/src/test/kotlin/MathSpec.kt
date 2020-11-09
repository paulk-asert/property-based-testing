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

import io.kotest.core.spec.style.StringSpec
import io.kotest.property.*
import kotlin.math.*

class MathSpec: StringSpec({

    "addition is commutative for integers" {
        forAll<Int, Int> { a, b ->
            a + b == b + a
        }
    }

    "multiplication is commutative for integers" {
        forAll<Int, Int> { a, b ->
            a * b == b * a
        }
    }

    "subtraction is not commutative for integers" {
        // ignore degenerate case of a == b
        forAll<Int, Int> { a, b ->
            a == b || a - b != b - a
        }
    }

    "subtraction is not commutative example" {
        assert (4 - 2 != 2 - 4)
    }

    "subtraction is commutative ignoring sign" {
        forAll<Int, Int> { a, b ->
            abs(a - b) == abs(b - a)
        }
    }

    "subtraction and addition are inverse functions" {
        forAll<Int, Int> { a, b ->
            a + b - b == a
        }
    }

    "addition is associative for integers" {
        forAll<Int, Int, Int> { a, b, c ->
            (a + b) + c == a + (b + c)
        }
    }

    "zero is the identity for integers" {
        forAll<Int> { i ->
            i + 0 == i && 0 + i == i
        }
    }

    "subtracting any integer from itself gives zero" {
        forAll<Int> { i ->
            i - i == 0
        }
    }

    "abs is idempotent" {
        forAll<Int> { i ->
            abs(i) == abs(abs(i))
        }
    }

    "times two is the same as add to self" {
        forAll<Int> { i ->
            i * 2 == i + i
        }
    }

    "pow two is the same as multiply to self" {
        forAll(Arb.int(0..40000)) { i ->
            i.toDouble().pow(2).toInt() == i * i
        }
    }
})
