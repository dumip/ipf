/*
 * Copyright 2008 the original author or authors.
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
package org.openehealth.ipf.platform.camel.core.support.transform.min;

import java.util.Collection;

import org.openehealth.ipf.commons.core.modules.api.Aggregator;

/**
 * @author Martin Krasser
 */
public class TestAggregator implements Aggregator<String, String> {

    public String zap(Collection<String> object, Object... params) {
        var buffer = new StringBuilder();
        for (var s : object) {
            buffer.append(":").append(s);
        }
        if (buffer.length() > 0) {
            buffer.deleteCharAt(0);
        }
        return buffer.toString();
    }

}
