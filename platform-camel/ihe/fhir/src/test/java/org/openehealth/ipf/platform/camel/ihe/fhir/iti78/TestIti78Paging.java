/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openehealth.ipf.platform.camel.ihe.fhir.iti78;

import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.ResourceType;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TestIti78Paging extends AbstractTestIti78 {

    private static final String CONTEXT_DESCRIPTOR = "iti-78-paging.xml";

    @BeforeClass
    public static void setUpClass() throws ServletException {
        startServer(CONTEXT_DESCRIPTOR);
    }


    @Test
    public void testSendManualPdqmWithCount() {

        Bundle page1 = sendManuallyWithCount(familyParameters(), 2);

        assertEquals(Bundle.BundleType.SEARCHSET, page1.getType());
        assertEquals(ResourceType.Bundle, page1.getResourceType());
        assertTrue(page1.hasEntry());
        assertEquals(3, page1.getTotal());
        assertEquals(2, page1.getEntry().size());

        Bundle page2 = nextPage(page1);
        assertEquals(Bundle.BundleType.SEARCHSET, page2.getType());
        assertEquals(ResourceType.Bundle, page2.getResourceType());
        assertTrue(page2.hasEntry());
        assertEquals(3, page2.getTotal());
        assertEquals(1, page2.getEntry().size());

    }
}
