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
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import net.jqwik.api.constraints.IntRange
import net.jqwik.engine.properties.arbitraries.DefaultMapArbitrary

// these tests pass but some values aren't as expected due to some Jsr308 limitations
class Idempotent {
    @Property
    void 'trim is idempotent'(
            @ForAll String s,
            @ForAll @IntRange(max=10) Integer i
    ) {
        def once = s.trim()
        def many = once
        i.times { many = many.trim() }
        assert once == many
    }

    @Property
    void 'unique is idempotent'(
            @ForAll List<@IntRange(min=0, max=100) Integer> items,
            @ForAll @IntRange(max=10) Integer i
    ) {
        def once = items.toUnique()
        def many = once
        i.times { many = many.toUnique() }
        assert once == many
    }

    @Property
    void 'map putAll is idempotent'(
            @ForAll('stringMap') Map<String, String> initial,
            @ForAll('stringMap') Map<String, String> additions,
            @ForAll @IntRange(max=10) Integer i
    ) {
        initial.putAll(additions)
        def saved = initial.clone()
        i.times { initial.putAll(additions) }
        assert initial == saved
    }

    @Provide
    Arbitrary<Map<String, String>> stringMap() {
        return new DefaultMapArbitrary(Arbitraries.strings().alpha().ofMaxLength(5).unique(),
                Arbitraries.strings().ofMaxLength(5))
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
}
