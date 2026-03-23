/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package ca.uqam.mgl7460.tp1;

import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uqam.mgl7460.tp1.drools.PretsHypothecairesRuleUnitData;
import ca.uqam.mgl7460.tp1.tests.CreationDemandesPret;
import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.modeles.Resultat;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RuleTestPretsHypothecaires {

    static final Logger LOG = LoggerFactory.getLogger(RuleTestPretsHypothecaires.class);

    @Test
    public void test() {
        LOG.info("Creating RuleUnit");
        PretsHypothecairesRuleUnitData pretsHypothecaireRuleUnitData = new PretsHypothecairesRuleUnitData();

        try (
            RuleUnitInstance<PretsHypothecairesRuleUnitData> pretsHypothecaireRuleUnitinstance = 
                        RuleUnitProvider.get().createRuleUnitInstance(pretsHypothecaireRuleUnitData)) {
            LOG.info("Insert data");
            DemandePret demandeOuiNonOui = CreationDemandesPret.getDemandePretOuiNonOui();
            pretsHypothecaireRuleUnitData.getDemandesPrets().add(demandeOuiNonOui);

            LOG.info("Execute les règles");
            int nombreReglesExecutees = pretsHypothecaireRuleUnitinstance.fire();

           
            // s'assurer que la demande a été refusée
            assertEquals(Resultat.REFUSEE, demandeOuiNonOui.getResultatTraitement().getResultat(),"Demande devrait être refusée");

            // et elle est supposé avoir échoué deux conditions
             assertEquals(2, nombreReglesExecutees, "La demande est supposée avoir échoué 2 conditions");
        }
    }
}