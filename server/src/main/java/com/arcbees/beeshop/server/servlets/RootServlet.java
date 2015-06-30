package com.arcbees.beeshop.server.servlets;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.arcbees.beeshop.server.Version;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

public class RootServlet extends HttpServlet {
    private static final long serialVersionUID = Version.VERSION;

    public static final String VELOCITY_PROPERTIES = "com/arcbees/beeshop/server/velocity.properties";
    public static final String TEMPLATE = "com/arcbees/beeshop/server/servlets/index.vm";

    private VelocityEngine velocityEngine;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VelocityContext context = new VelocityContext();

        getVelocityEngine().mergeTemplate(TEMPLATE, "UTF-8" , context, response.getWriter());
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
            throw new RuntimeException("Unable to load velocity properties from '" + VELOCITY_PROPERTIES + "'." , e);
        }

        return properties;
    }
}
