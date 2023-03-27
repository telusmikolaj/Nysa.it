package pl.com.great.nysa.it.image.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.com.great.nysa.it.image.model.ProductInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3ImageService {
    private final AmazonS3 s3Client;
    private final String bucketName = "nysa.it";

    private final String ACCESS_KEY = "AKIA232P45UEXYDKYP4M";

    private final String SECRET_KEY =  "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";

    private final String REGION = "eu-central-1";

    public S3ImageService() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(REGION)
                .build();
    }

    public String uploadImage(String fileName, InputStream inputStream) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        metadata.setContentLength(inputStream.available());
        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
        s3Client.putObject(request);
        return s3Client.getUrl(bucketName, fileName).toString();
    }

    public List<ProductInfo> detectProduct(MultipartFile image) throws IOException {
        List<ProductInfo> productInfoList = new ArrayList<>();

        // Convert the MultipartFile to a ByteString
        ByteString imgBytes = ByteString.copyFrom(image.getBytes());

        // Create an Image object from the ByteString
        Image img = Image.newBuilder().setContent(imgBytes).build();

        // Initialize the Google Cloud Vision API client
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
            // Prepare the feature to be detected
            Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            List<AnnotateImageRequest> requests = new ArrayList<>();
            requests.add(request);

            // Send the request to the API
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            // Process the API response and extract product information
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.printf("Error: %s%n", res.getError().getMessage());
                    return productInfoList;
                }

                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                    productInfoList.add(new ProductInfo(annotation.getDescription(), annotation.getScore()));
                }
            }
        } catch (IOException e) {
            // Handle the exception
            System.out.printf("Error occurred while detecting product: %s%n", e.getMessage());
            return productInfoList;
        }

        return productInfoList;
    }
}


