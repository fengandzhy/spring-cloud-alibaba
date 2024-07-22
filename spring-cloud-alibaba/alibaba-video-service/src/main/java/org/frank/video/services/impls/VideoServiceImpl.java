package org.frank.video.services.impls;

import org.frank.domains.Video;
import org.frank.video.repositories.VideoMapper;
import org.frank.video.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    
    private VideoMapper videoMapper;
    
    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public int save(Video video) {
        return videoMapper.save(video);
    }

    @Autowired
    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }
}
