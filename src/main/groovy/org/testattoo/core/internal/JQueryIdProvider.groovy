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

import org.testattoo.core.ComponentException
import org.testattoo.core.Config
import org.testattoo.core.IdProvider
import org.testattoo.core.MetaInfo

import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class JQueryIdProvider implements IdProvider {
    private static Logger logger = Logger.getLogger("org.testattoo.core")

    final String expression
    final boolean singleElement

    IdProvider get(String expression, boolean singleElement) {
        return new JQueryIdProvider(expression, singleElement)
    }

    JQueryIdProvider() {}

    private JQueryIdProvider(String expression, boolean singleElement) {
        this.expression = expression.startsWith('$') ? expression : ('$(\'' + expression + '\')')
        this.singleElement = singleElement
    }

    @Override
    List<MetaInfo> metaInfos() throws ComponentException {
        logger.log(Level.INFO, "metaInfos: ${expression}")
        List<MetaInfo> metaInfos = Config.provider.metaInfo(expression)
        if (singleElement) {
            if (metaInfos.size() == 1) return metaInfos
            if (metaInfos.size() == 0) throw new ComponentException("Component defined by expression ${expression} not found.")
            throw new ComponentException("Component defined by expression ${expression} is not unique: got ${metaInfos.size()}")
        }
        return metaInfos
    }
}
