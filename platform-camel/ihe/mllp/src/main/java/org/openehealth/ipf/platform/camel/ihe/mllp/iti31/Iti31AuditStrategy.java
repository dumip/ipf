/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.ihe.mllp.iti31;

import ca.uhn.hl7v2.model.Message;
import org.apache.camel.Exchange;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpAuditStrategy;
import org.openhealthtools.ihe.atna.auditor.codes.rfc3881.RFC3881EventCodes.RFC3881EventOutcomeCodes;

public abstract class Iti31AuditStrategy extends MllpAuditStrategy<Iti31AuditDataset> {


    public Iti31AuditStrategy(boolean serverSide) {
        super(serverSide);
    }

    @Override
    public void enrichAuditDatasetFromRequest(Iti31AuditDataset auditDataset, Message msg, Exchange exchange) {
        Iti31AuditStrategyUtils.enrichAuditDatasetFromRequest(auditDataset, msg, exchange);
    }

    @Override
    public void doAudit(RFC3881EventOutcomeCodes eventOutcome, Iti31AuditDataset auditDataset) {
        if (auditDataset.isUpdate()) {
            callUpdateAuditRoutine(eventOutcome, auditDataset, true);
        } else if (auditDataset.isDelete()) {
            callDeleteAuditRoutine(eventOutcome, auditDataset, false);
        } else if (auditDataset.isMerge()) {
            callDeleteAuditRoutine(eventOutcome, auditDataset, false);
            callUpdateAuditRoutine(eventOutcome, auditDataset, true);
        } else if (auditDataset.isCreate()) {
            callCreateAuditRoutine(eventOutcome, auditDataset, true);
        }
    }

    protected abstract void callCreateAuditRoutine(
            RFC3881EventOutcomeCodes eventOutcome,
            Iti31AuditDataset auditDataset,
            boolean newPatientId);

    protected abstract void callUpdateAuditRoutine(
            RFC3881EventOutcomeCodes eventOutcome,
            Iti31AuditDataset auditDataset,
            boolean newPatientId);

    protected abstract void callDeleteAuditRoutine(
            RFC3881EventOutcomeCodes eventOutcome,
            Iti31AuditDataset auditDataset,
            boolean newPatientId);

    @Override
    public Iti31AuditDataset createAuditDataset() {
        return new Iti31AuditDataset(isServerSide());
    }

}