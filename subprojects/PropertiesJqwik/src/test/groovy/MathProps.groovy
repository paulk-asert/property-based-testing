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

import net.jqwik.api.*
import net.jqwik.api.constraints.*

import java.math.RoundingMode

class MathProps {
    @Property
    @Disabled('enable to see failure due to overflow')
    boolean 'absolute value of all integers is positive'(@ForAll int i) {
        i.abs() >= 0
    }

    @Property
    boolean 'absolute value of all numbers is positive excluding MIN_VALUE'(@ForAll int i) {
        i == Integer.MIN_VALUE || i.abs() >= 0
    }

    @Property
    boolean 'absolute value of all -ve numbers is positive'(@ForAll @Positive int anInteger) {
        Math.abs(-anInteger) >= 0
    }

    @Property
    void 'round up should be greater than round down'(@ForAll Double d) {
        BigDecimal bd = new BigDecimal(d)
        def up = bd.setScale(2, RoundingMode.HALF_UP)
        def down = bd.setScale(2, RoundingMode.HALF_DOWN)
        assert up >= down
    }

    @Property
    void 'rounding down should yield smaller value'(@ForAll Double d) {
        Math.floor(d) <= d
    }
}