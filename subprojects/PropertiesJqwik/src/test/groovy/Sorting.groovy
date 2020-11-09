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
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Report
import net.jqwik.api.Reporting
import net.jqwik.api.constraints.IntRange

// these tests pass but some values aren't as expected due to some Jsr308 limitations
class Sorting {
    @Property
    boolean 'sort preserves size'(
            @ForAll List<String> items
    ) {
        items.size() == items.sort().size()
    }

    @Property
    void 'sort is idempotent'(
            @ForAll List<@IntRange(min=0, max=100) Integer> items,
            @ForAll @IntRange(max=10) Integer i
    ) {
        def once = items.sort()
        def many = once
        i.times { many = many.sort() }
        assert once == many
    }

    @Property
    void 'sort actually sorts'(
            @ForAll List<@IntRange(min=0, max=100) Integer> items
    ) {
        def sorted = items.sort()
        assert sorted.collate(2, 1, false).every { first, second ->
            first <= second
        }
    }

    @Property
    @Report([Reporting.GENERATED, Reporting.FALSIFIED])
    void 'show broken sort detected'(
            @ForAll List<@IntRange(min=0, max=100) Integer> items
    ) {
        def sorted = brokenSort(items)
        assert sorted.toSorted() == sorted
    }

    static brokenSort(List items) {
        if (items.size() == 2) return items
        items.sort()
    }
}
