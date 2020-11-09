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

import org.scalacheck.Prop._
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import scala.math.pow

class MathSpec extends AnyPropSpec with ScalaCheckDrivenPropertyChecks {
  property("addition is commutative for integer") {
    forAll { (a: Int, b: Int) =>
      assert (a + b == b + a)
    }
  }

  property("multiplication is commutative for integer") {
    forAll { (a: Int, b: Int) =>
      assert (a * b == b * a)
    }
  }

  // ignore degenerate case of a == b and involving MinValue
  property("subtraction is not commutative for integer") {
    forAll { (a: Int, b: Int) =>
      whenever(a != b &&
        !(a == 0 && b == Int.MinValue) &&
        !(a == Int.MinValue && b == 0)
      ) { assert (a - b != b - a) }
    }
  }

  property("subtraction is not commutative example") {
    assert (4 - 2 != 2 - 4)
  }

  property("subtraction is commutative ignoring sign") {
    forAll { (a: Int, b: Int) =>
      assert ((a - b).abs == (b - a).abs)
    }
  }

  property("subtraction and addition are inverse functions") {
    forAll { (a: Int, b: Int) =>
      assert (a + b - b == a)
    }
  }

  property("addition is associative for integer") {
    forAll { (a: Int, b: Int, c: Int) =>
      assert ((a + b) + c == a + (b + c))
    }
  }

  property("zero is the identity for integer") {
    forAll { (i: Int) =>
      assert (i + 0 == i)
      assert (0 + i == i)
    }
  }

  property("subtracting any integer from itself gives zero") {
    forAll { (i: Int) =>
      assert (i - i == 0)
    }
  }

  property("abs is idempotent") {
    forAll { (i: Int) =>
      assert (i.abs == i.abs.abs)
    }
  }

  property("times two is the same as add to self") {
    forAll { (i: Int) =>
      assert (i * 2 == i + i)
    }
  }

  // limit range to avoid overflow
  property("pow two is the same as multiply to self") {
    forAll { (i: Int) =>
      whenever(i < 40000 && i > -40000) { assert (pow(i, 2) == i * i) }
    }
  }
}
