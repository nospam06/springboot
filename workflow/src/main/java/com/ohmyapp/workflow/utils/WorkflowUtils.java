package com.ohmyapp.workflow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * author by lip on 12/30/2015.
 */
public class WorkflowUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowUtils.class);
    private WorkflowUtils() {
        // static access only
    }

    public static String loadFromFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }

    public static String loadClassPathResource(String path) {
        byte[] byteArray = new byte[4096];
        try (InputStream is = WorkflowUtils.class.getResourceAsStream(path)) {
            StringBuilder sb = new StringBuilder();
            int bytesRead = 0;
            while ((bytesRead = is.read(byteArray)) >= 0) {
                sb.append(new String(byteArray, 0, bytesRead, StandardCharsets.UTF_8));
            }
            return sb.toString();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }
}
