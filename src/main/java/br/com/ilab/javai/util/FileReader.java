package br.com.ilab.javai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class FileReader {
    public static void readFile(ResponseInputStream<GetObjectResponse> file)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
