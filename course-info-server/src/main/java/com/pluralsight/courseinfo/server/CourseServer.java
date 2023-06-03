package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {
    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }
    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);

    public static void main(String... args) {
        String databaseFilename = loadDataBaseFilename();
        LOG.info("Starting HTTP Server with database {}", databaseFilename);
        CourseRepository courseRepository = CourseRepository.openCourseRepository(databaseFilename);
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(loadBaseURI()), config);
    }

    private static String loadDataBaseFilename() {
        try (InputStream propertiesStream =
                CourseServer.class.getResourceAsStream("/server.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            return properties.getProperty("course-info.database");
        } catch (IOException e) {
            throw new IllegalStateException("Could not load database filename", e);
        }
    }
    private static String loadBaseURI() {
        try (InputStream propertiesStream =
                CourseServer.class.getResourceAsStream("/server.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            return properties.getProperty("base-URI");
        } catch (IOException e) {
            throw new IllegalStateException("Could not load base URI", e);
        }
    }
}
