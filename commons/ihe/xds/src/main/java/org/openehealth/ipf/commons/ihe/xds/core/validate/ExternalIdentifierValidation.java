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
package org.openehealth.ipf.commons.ihe.xds.core.validate;

import static org.openehealth.ipf.commons.ihe.xds.core.validate.ValidationMessage.*;
import static org.openehealth.ipf.commons.ihe.xds.core.validate.ValidatorAssertions.*;

import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRegistryObject;

/**
 * Validates an external identifier.
 * @author Jens Riemschneider
 */
public class ExternalIdentifierValidation implements RegistryObjectValidator {
    private final String scheme;
    private final ValueValidator validator;

    /**
     * Constructs a validation.
     * @param scheme
     *          the scheme of the external identifier to validate.
     * @param validator
     *          the validator to call for the value of the external identifier.
     */
    public ExternalIdentifierValidation(String scheme, ValueValidator validator) {
        this.scheme = scheme;
        this.validator = validator;
    }

    @Override
    public void validate(EbXMLRegistryObject obj) throws XDSMetaDataException {
        var value = obj.getExternalIdentifierValue(scheme);
        metaDataAssert(value != null, MISSING_EXTERNAL_IDENTIFIER, scheme);
        
        validator.validate(value);
    }
}
