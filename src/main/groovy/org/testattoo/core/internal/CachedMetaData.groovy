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
import org.testattoo.core.IdProvider
import org.testattoo.core.MetaDataProvider
import org.testattoo.core.MetaInfo
import org.testattoo.core.component.Component

import static org.testattoo.core.internal.Identifiers.identifyingExpression

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CachedMetaData implements MetaDataProvider {
    private MetaInfo metaInfo
    private IdProvider idProvider

    @Override
    MetaInfo metaInfo(Component c) {
        if (!metaInfo) {
            MetaInfo info = idProvider.metaInfos()[0]
            if (c.class != Component) {
                String identifyingExpr = identifyingExpression(c.class)
                if (!(c.provider.check(info.id, identifyingExpr))) {
                    Class<Component> type = metaInfo.componentTypes.find {
                        c.provider.check(info.id, identifyingExpression(it))
                    }
                    throw new ComponentException("Expected a ${c.class.simpleName} for component with id '${info.id}', but was: ${type?.simpleName ?: 'unknown'}")
                }
            }
            metaInfo = info
        }
        return metaInfo
    }

    @Override
    List<MetaInfo> metaInfos() throws ComponentException {
        idProvider.metaInfos()
    }
}
