package org.utils.qa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import io.restassured.response.Response;

public class ResponseUtils {

	public static void saveResponse(String testName, Response response) {

        try {
            File f = new File(System.getProperty("user.dir") + "/failed-responses");

            if (!f.exists()) {
                f.mkdirs();
            }

            File file = new File(f + "/" + testName + ".json");

            FileWriter writer = new FileWriter(file);
            writer.write(response.asPrettyString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
