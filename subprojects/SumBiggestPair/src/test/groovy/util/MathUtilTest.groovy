package util
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

import static util.MathUtil.sumBiggestPair

class MathUtilTest {
    @Test
    void examples() {
        assert sumBiggestPair(5, 4, 1) == 9
        assert sumBiggestPair(4, 5, 1) == 9
        assert sumBiggestPair(5, 9, 6) == 15
        assert sumBiggestPair(10, 2, 6) == 16 // 100% coverage at this point
        assert sumBiggestPair(2, 5, 6) == 11
        assert sumBiggestPair(5, 2, 6) == 11
    }
}

