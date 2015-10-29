/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.arcbees.beeshop.server.servlets;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.arcbees.beeshop.server.Version;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

@Singleton
public class RootServlet extends HttpServlet {
    private static final long serialVersionUID = Version.VERSION;

    public static final String VELOCITY_PROPERTIES = "com/arcbees/beeshop/server/velocity.properties";
    public static final String TEMPLATE = "com/arcbees/beeshop/server/servlets/index.vm";

    private VelocityEngine velocityEngine;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String locale = new LocaleExtractor(request, response).extractLocale();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.arcbees.beeshop.server.seo", Locale.forLanguageTag(locale));

        VelocityContext context = new VelocityContext();
        context.put("locale", locale);
        context.put("seo", resourceBundle);

        getVelocityEngine().mergeTemplate(TEMPLATE, "UTF-8", context, response.getWriter());
    }

    private VelocityEngine getVelocityEngine() {
        if (velocityEngine == null) {
            Properties properties = loadVelocityProperties();
            velocityEngine = new VelocityEngine(properties);
        }

        return velocityEngine;
    }

    private Properties loadVelocityProperties() {
        Properties properties = new Properties();

        try {
            URL url = Resources.getResource(VELOCITY_PROPERTIES);
            ByteSource byteSource = Resources.asByteSource(url);

            properties.load(byteSource.openStream());
        } catch (IOException e) {
            throw new RuntimeException("Unable to load velocity properties from '" + VELOCITY_PROPERTIES + "'.", e);
        }

        return properties;
    }
}
