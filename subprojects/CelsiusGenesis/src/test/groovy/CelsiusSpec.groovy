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
//@Grab('com.nagternal:spock-genesis:0.6.0')
//@GrabExclude('org.codehaus.groovy:groovy-all')
import spock.genesis.transform.Iterations
import spock.lang.Specification

import static Converter.celsius
import static java.lang.Math.round
import static spock.genesis.Gen.integer

class CelsiusSpec extends Specification {
    def liquidC = 0..100
    def liquidF = 32..212

    @Iterations(100)
    def "test phase maintained"() {
        given:
        int tempF = integer(-40..240).iterator().next()

        when:
        int tempC = round(celsius(tempF))

        then:
        tempC <= tempF
        tempC in liquidC == tempF in liquidF
    }

    @Iterations(100)
    def "test order maintained"() {
        given:
        int tempF1 = integer(-273..999).iterator().next()
        int tempF2 = integer(-273..999).iterator().next()

        when:
        int tempC1 = round(celsius(tempF1))
        int tempC2 = round(celsius(tempF2))

        then:
        (tempF1 <=> tempF2) == (tempC1 <=> tempC2)
    }
}
