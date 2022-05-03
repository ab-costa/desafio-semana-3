package br.com.ilab.javai.util;

import java.io.IOException;
import java.io.InputStream;

import br.com.ilab.javai.auth.AWSCredentials;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3UploadFile {
    private static final String BUCKET = "grupo-5-bucket";
    private static final Region REGION = Region.US_EAST_1; // add to export

    public static void uploadFile(String fileName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {

        S3Client client = S3Client.builder()
                .region(REGION)
                .credentialsProvider(AWSCredentials.getCredentials())
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .acl("public-read")
                .contentType("text/csv")
                .build();

        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}
