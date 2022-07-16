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
package util.bug;

public class MathUtil {
    public static int sumBiggestPair(int a, int b, int c) {
        int op1 = a;
        int op2 = b;
        if (c > a) {
            op1 = c;
        } else if (c > b) {
            op2 = c;
        }
        return op1 + op2;
    }

    private MathUtil() {}
}
