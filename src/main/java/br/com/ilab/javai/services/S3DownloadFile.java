package br.com.ilab.javai.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.ilab.javai.auth.AWSCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class S3DownloadFile {
    public static void main(String[] args) throws IOException {
        final String BUCKET = "grupo-5-bucket";
        final String KEY_NAME = "planilha-cliente.csv";

        S3Client client = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(AWSCredentials.getCredentials())
                .build();

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(BUCKET)
                .key(KEY_NAME)
                .build();

        ResponseInputStream<GetObjectResponse> inputStream = client.getObject(request);

        readFile(inputStream);

        inputStream.close();
    }

    public static void readFile(ResponseInputStream<GetObjectResponse> file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}