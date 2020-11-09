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
//@Grab('net.java.quickcheck:quickcheck:0.6')
import net.java.quickcheck.Generator
import net.java.quickcheck.generator.support.DefaultFrequencyGenerator
import org.junit.jupiter.api.Test

import java.text.SimpleDateFormat

import static net.java.quickcheck.generator.CombinedGenerators.excludeValues
import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.anyEnumValue
import static net.java.quickcheck.generator.PrimitiveGenerators.dates
import static net.java.quickcheck.generator.PrimitiveGenerators.fixedValues
import static net.java.quickcheck.generator.PrimitiveGenerators.integers
import static net.java.quickcheck.generator.distribution.Distribution.INVERTED_NORMAL

class QuickCheck {
    static enum Nums {
        ONE, TWO, SIX, TEN
    }

    static class MonthGenerator implements Generator<String> {
        Generator<Date> genDate = dates()
        def sdf = new SimpleDateFormat('MMM', Locale.ENGLISH)
        String next() { sdf.format(genDate.next()) }
    }

    /**
     * quickcheck example illustrating combining generators and numeric distributions
     * randomly returns 3-letter pets, 3-letter months, 3-letter enums, or 3-digit numbers
     */
    @Test
    void testGenerators() {
        def pets = fixedValues(['Ant', 'Bee', 'Cat', 'Dog'])
        def nums = excludeValues(integers(100, 999, INVERTED_NORMAL), 500..599)
        def months = new MonthGenerator()
        def enums = new Generator() {
            String next() { anyEnumValue(Nums) }
        }

        def gen = new DefaultFrequencyGenerator(pets, 40)
                .add(nums, 30)
                .add(months, 20)
                .add(enums, 10)

        50.times {
            def next = gen.next().toString()
            print "$next "
            assert next.size() == 3
            if (it % 10 == 9) println()
        }
        println()
    }
}