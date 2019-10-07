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
package org.testattoo.core.internal

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription
import org.testattoo.core.Config

import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Wait {
    private static Logger logger = Logger.getLogger("org.testattoo.core")

    static void waitUntil(Closure c, Matcher what = null) {
        boolean success = false
        long timeout = Config.waitUntil.toMillis()
        long interval = 200

        logger.log(Level.INFO, "WaitUntil: " + timeout)
        for (; timeout > 0; timeout -= interval) {
            try {
                if (what ? what.matches(c.delegate) : c()) {
                    success = true
                    break
                }
            } catch (e) {
                logger.log(Level.INFO, 'Matcher evaluation fail with this exception : ' + e.message)
                logger.log(Level.INFO, 'Retrying...')
            }
            Thread.sleep(interval)
        }

        if (!success) {
            Description description = new StringDescription()
            description.appendText('Unable to reach the condition after ' + Config.waitUntil.toMillis() + ' milliseconds')
            if (what) {
                description.appendText('\nExpected: ')
                    .appendDescriptionOf(what)
                    .appendText('\n     but: ')
                what.describeMismatch(c.delegate, description)
            }
            throw new AssertionError(description.toString())
        }
    }
}
