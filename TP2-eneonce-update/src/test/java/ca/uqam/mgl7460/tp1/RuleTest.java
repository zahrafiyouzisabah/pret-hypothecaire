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

import java.util.List;

import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uqam.mgl7460.tp1.drools.Measurement;
import ca.uqam.mgl7460.tp1.drools.MeasurementUnit;
import ca.uqam.mgl7460.tp1.drools.UtilitaireRegles;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleTest {

    static final Logger LOG = LoggerFactory.getLogger(RuleTest.class);

    @Test
    public void testOldAPI() {
        // 1.   Get the name of the rules package
        String nomFichierRegles = "src/main/resources/ca/uqam/mgl7460/tp1/drools/rules.drl";

        // 2.   Execute it
        KieSession kieSession = UtilitaireRegles.getKieSessionPourFichier(nomFichierRegles);
        kieSession.insert(new Measurement("color", "red"));
        kieSession.insert(new Measurement("color", "green"));
        kieSession.insert(new Measurement("color", "blue"));
        System.out.println("Trying to execute rules in "+ nomFichierRegles + " with kiesession: " + kieSession);
        try {
            kieSession.fireAllRules();
            System.out.println("Finished executing rules in "+ nomFichierRegles + " with kiesession: " + kieSession);
        } finally {
            kieSession.dispose();
        }
    
    }
}