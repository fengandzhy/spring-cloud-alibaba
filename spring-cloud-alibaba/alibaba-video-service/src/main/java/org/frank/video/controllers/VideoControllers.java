package org.frank.video.controllers;

import org.frank.video.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/video")
public class VideoControllers {
    
    private VideoService videoService;
    
    @RequestMapping("/find/{id}")
    public Object getVideoById(@PathVariable int id){
        return videoService.findById(id);
    }

    @RequestMapping("/find")
    public Object findVideoById(@RequestParam int videoId){
        return videoService.findById(videoId);
    }

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }
}
