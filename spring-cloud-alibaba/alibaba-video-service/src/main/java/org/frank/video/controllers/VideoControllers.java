package org.frank.video.controllers;

import org.frank.domains.Video;
import org.frank.video.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    
    @PostMapping("/save")
    public int saveVideo(@RequestBody Video video){
        return videoService.save(video);
    }
    

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }
}
