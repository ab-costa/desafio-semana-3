package br.com.ilab.javai.util;

import java.io.IOException;

import br.com.ilab.javai.auth.AWSCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class S3DownloadFile {
    private static final Region REGION = Region.US_EAST_1;
    private static final String BUCKET = "grupo-5-bucket";

    public static void downloadFile(String filename) throws IOException {
        S3Client client = S3Client.builder()
                .region(REGION)
                .credentialsProvider(AWSCredentials.getCredentials())
                .build();

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(BUCKET)
                .key(filename)
                .build();

        ResponseInputStream<GetObjectResponse> inputStream = client.getObject(request);

        FileReader.readFile(inputStream);

        inputStream.close();
    }
}