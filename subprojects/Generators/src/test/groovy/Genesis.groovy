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

import groovy.transform.Immutable
import spock.genesis.Gen
import spock.lang.Specification

class Genesis extends Specification {
    @Immutable
    static class Person {
        int id
        String name
        Date birthDate
        String title
        char gender
    }

    def 'test person'() {
        expect:
        p.name instanceof String && p.name[0] in 'A'..'Z' && p.name[-1] in 'a'..'z'
        p.birthDate instanceof Date
        p.id instanceof Integer && p.id < 10_000
        p.title?.size() in [null, 0, 2, 3]
        where:
        p << Gen.type(Person,
                id: Gen.integer(1..9999),
                name: Gen.string(~/[A-Z][a-z]+( [A-Z][a-z]+)?/),
                birthDate: Gen.date(Date.parse('MM/dd/yyyy', '01/01/1980'), new Date()),
                title: Gen.these('', null).then(Gen.any('Dr', 'Mr', 'Ms', 'Mrs', 'Mx')),
                gender: Gen.character('MFX')).take(5)
    }
}
