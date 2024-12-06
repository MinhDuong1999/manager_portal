package com.example.manager_users.common.repository.httpclient;

import com.example.manager_users.user.dto.request.ProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.services.profiles}")
public interface ProfileClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void createProfile(@RequestBody ProfileRequest request);
}
