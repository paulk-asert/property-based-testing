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
import net.jqwik.api.Arbitrary
import net.jqwik.api.Disabled
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import net.jqwik.api.constraints.IntRange

import static util.MathUtil.sumBiggestPair

class MathUtilTest {

    @Property
    void "result should be bigger than any individual and smaller than sum of all"(
            @ForAll @IntRange(min = 0, max = 1000) Integer a,
            @ForAll @IntRange(min = 0, max = 1000) Integer b,
            @ForAll @IntRange(min = 0, max = 1000) Integer c) {
        def result = sumBiggestPair(a, b, c)
        assert [a, b, c].every { individual -> result >= individual }
        assert result <= a + b + c
    }

    @Property
    void "sum of any pair should not be greater than result"(
            @ForAll @IntRange(min = 0, max = 1000) Integer a,
            @ForAll @IntRange(min = 0, max = 1000) Integer b,
            @ForAll @IntRange(min = 0, max = 1000) Integer c) {
        def result = sumBiggestPair(a, b, c)
        assert [a + b, b + c, c + a].every { sumOfPair -> result >= sumOfPair }
    }

    @Property
    void "result should be the same as alternative oracle implementation with small ints"(
            @ForAll @IntRange(min = 0, max = 1000) Integer a,
            @ForAll @IntRange(min = 0, max = 1000) Integer b,
            @ForAll @IntRange(min = 0, max = 1000) Integer c) {
        assert sumBiggestPair(a, b, c) == [a+b, a+c, b+c].max()
    }

    @Property
    void checkShort(@ForAll Short a, @ForAll Short b, @ForAll Short c) {
        assert sumBiggestPair(a, b, c) == [a+b, a+c, b+c].max()
    }

    @Property
    @Disabled('result should be the same as alternative oracle implementation')
    void "result should be the same as alternative oracle implementation"(@ForAll Integer a, @ForAll Integer b, @ForAll Integer c) {
        assert sumBiggestPair(a, b, c) == [a+b, a+c, b+c].max()
    }

    @Property
    void checkIntegerConstrained(@ForAll @IntRange(min = -1_000_000_000, max = 1_000_000_000) Integer a,
                                 @ForAll @IntRange(min = -1_000_000_000, max = 1_000_000_000) Integer b,
                                 @ForAll @IntRange(min = -1_000_000_000, max = 1_000_000_000) Integer c) {
        assert sumBiggestPair(a, b, c) == [a+b, a+c, b+c].max()
    }

    @Property
    void checkIntegerConstrainedProvider(@ForAll('halfMax') Integer a,
                                         @ForAll('halfMax') Integer b,
                                         @ForAll('halfMax') Integer c) {
        assert sumBiggestPair(a, b, c) == [a+b, a+c, b+c].max()
    }

    @Provide
    Arbitrary<Integer> halfMax() {
        int halfMax = Integer.MAX_VALUE >> 1
        return Arbitraries.integers().between(-halfMax, halfMax)
    }

    @Property
    void checkIntegerWithLongCalculations(@ForAll Integer a, @ForAll Integer b, @ForAll Integer c) {
        def (al, bl, cl) = [a, b, c]*.toLong()
        assert sumBiggestPair(a, b, c) == [al+bl, al+cl, bl+cl].max().toInteger()
    }

}
