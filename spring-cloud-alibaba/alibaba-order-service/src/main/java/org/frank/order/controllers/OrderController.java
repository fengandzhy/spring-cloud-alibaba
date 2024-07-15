package org.frank.order.controllers;


import org.frank.domains.Video;
import org.frank.domains.VideoOrder;
import org.frank.order.feign.client.VideoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    
    private RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;
    
    private VideoFeignClient videoFeignClient;
    
    @RequestMapping("/find/{videoId}")
    public VideoOrder getOrderById(@PathVariable int videoId){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("alibaba-video-service");
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
//        String url = "http://" + serviceInstance.getHost()+":"+serviceInstance.getPort()+"/api/v1/video/"+id;
//        String url = "http://alibaba-video-service/api/v1/video/"+id; // 这里只有用Ribbon 才能用这种方式调用 
//        Video video = restTemplate.getForObject(url, Video.class);
//        Video video = videoFeignClient.getById(videoId);
        Video video = videoFeignClient.getById(videoId);
        VideoOrder order = new VideoOrder();
        order.setVideoId(video.getId());
        order.setVideoTitle(video.getTitle());        
        return order;
    }

    @RequestMapping("/find")
    public VideoOrder findOrderById(@RequestParam int videoId){        
        Video video = videoFeignClient.findById(videoId);
        VideoOrder order = new VideoOrder();
        order.setVideoId(video.getId());
        order.setVideoTitle(video.getTitle());
        return order;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Autowired
    public void setVideoFeignClient(VideoFeignClient videoFeignClient) {
        this.videoFeignClient = videoFeignClient;
    }
}
