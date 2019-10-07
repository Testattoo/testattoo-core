/**
 * Copyright © 2019 Testattoo (altus34@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testattoo.core.hamcrest.matcher.property

import org.hamcrest.Description
import org.testattoo.core.hamcrest.PropertyMatcher
import org.testattoo.core.support.property.LengthSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class LengthMatcher extends PropertyMatcher<LengthSupport> {
    private Object length

    LengthMatcher(Object length) {
        this.length = length
    }

    @Override
    protected boolean matchesSafely(LengthSupport component) {
        component.length() == length
    }

    @Override
    void describeTo(Description description) {
        description.appendText('length ' + length.toString())
    }

    @Override
    protected void describeMismatchSafely(LengthSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has length ' + component.length().toString())
    }
}
