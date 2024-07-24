package org.frank.video.controllers;

import org.frank.domains.Video;
import org.frank.video.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/video")
public class VideoControllers {
    
    private VideoService videoService;
    
    @RequestMapping(value ="/find/{id}", produces = "application/json")    
    public Video getVideoById(@PathVariable int id){
        Video video = videoService.findById(id);
        return video;
    }

    @RequestMapping(value = "/find", produces = "application/json")
    public Video findVideoById(@RequestParam int videoId){
        return videoService.findById(videoId);
    }
    
    @PostMapping("/save")
    public int saveVideo(@RequestBody Video video){
        return videoService.save(video);
    }
    

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }
}
