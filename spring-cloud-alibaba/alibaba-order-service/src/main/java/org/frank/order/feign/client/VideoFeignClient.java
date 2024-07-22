package org.frank.order.feign.client;

import org.frank.domains.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="alibaba-video-service")
public interface VideoFeignClient {

    @GetMapping(value = "/api/v1/video/find")
    Video findById(@RequestParam("videoId") int videoId);

    @GetMapping(value = "/api/v1/video/find/{videoId}")
    Video getById(@PathVariable("videoId") int videoId);

    @PostMapping(value = "/api/v1/video/save")
    int saveVideo(@RequestBody Video video);
}
