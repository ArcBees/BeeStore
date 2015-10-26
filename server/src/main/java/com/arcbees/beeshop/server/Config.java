/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.beeshop.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties;

    public Config() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        InputStream in = getClass().getResourceAsStream("Config.properties");
        try {
            properties.load(in);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Failed to load config file.");
        }
    }

    public String stripePrivateKey() {
        return properties.getProperty("stripePrivateKey");
    }
}
