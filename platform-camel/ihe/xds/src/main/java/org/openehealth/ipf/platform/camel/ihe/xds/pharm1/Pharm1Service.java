/*
 * Copyright 2009 the original author or authors.
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

package org.openehealth.ipf.platform.camel.ihe.xds.pharm1;

import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.AdhocQueryRequest;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.AdhocQueryResponse;
import org.openehealth.ipf.commons.ihe.xds.pharm1.Pharm1PortType;
import org.openehealth.ipf.platform.camel.ihe.xds.XdsAdhocQueryService;

/**
 * Service implementation for the IHE PHARM-1 transaction (Query Pharmacy Documents).
 * <p>
 * This implementation delegates to a Camel consumer by creating an exchange.
 *
 * @author Quentin Ligier
 */
public class Pharm1Service extends XdsAdhocQueryService implements Pharm1PortType {

    public Pharm1Service() {
        super((String)null);
    }

    @Override
    public AdhocQueryResponse communityPharmacyManagerQueryPharmacyDocuments(AdhocQueryRequest body) {
        return this.processRequest(body);
    }
}
