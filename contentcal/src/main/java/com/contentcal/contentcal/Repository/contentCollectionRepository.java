package com.contentcal.contentcal.Repository;

import org.springframework.stereotype.Repository;
import com.contentcal.contentcal.model.Content;
import com.contentcal.contentcal.model.Status;
import com.contentcal.contentcal.model.Type;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class contentCollectionRepository {
    
    private final List<Content> contentList = new ArrayList<>();

    public contentCollectionRepository(){

    }

    public List<Content> find_All(){
        return contentList;
    }

    public Optional<Content> findById (Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content){
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

     @PostConstruct
    public void init(){
        Content content = new Content(1,"my blog","This is mt blog post",
        Status.IDEA,Type.ARTICLE,LocalDateTime.now(),null,"");

        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void deleteById(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}
