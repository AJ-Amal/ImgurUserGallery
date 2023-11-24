package com.image.photos.ImgurApiService;

import com.image.photos.ImgurApiService.api.ImgurApiResponse;
import com.image.photos.exception.ImgurApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImgurApiService {

    @Value("${imgur.clientId}")
    private String clientId;

    @Value("${imgur.uploadEndpoint}")
    private String uploadEndpoint;

    @Value("${imgur.deleteEndpoint}")
    private String deleteEndpoint;

    public String uploadImage(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<ImgurApiResponse> responseEntity = new RestTemplate().exchange(
                    uploadEndpoint + "?client_id=" + clientId,
                    HttpMethod.POST,
                    requestEntity,
                    ImgurApiResponse.class
            );

            ImgurApiResponse imgurApiResponse = responseEntity.getBody();
            if (imgurApiResponse != null && imgurApiResponse.isSuccess()) {
                return imgurApiResponse.getData().getLink();
            } else {
                throw new ImgurApiException("Image upload failed: " + imgurApiResponse.getError());
            }
        } catch (HttpClientErrorException e) {
            throw new ImgurApiException("Image upload failed: " + e.getMessage());
        }
    }

    public void deleteImage(String imageUrl) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Client-ID " + clientId);

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            new RestTemplate().exchange(
                    deleteEndpoint + "/" + extractImageId(imageUrl),
                    HttpMethod.DELETE,
                    requestEntity,
                    Void.class
            );
        } catch (HttpClientErrorException e) {
            throw new ImgurApiException("Image deletion failed: " + e.getMessage());
        }
    }

    private String extractImageId(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }
}
