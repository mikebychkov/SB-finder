package org.mike.finder.kafka;

import org.mike.finder.model.Result;
import org.mike.finder.repo.ResultRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.List;

//@EnableBinding(CustomChannels.class)
public class PostsChangeHandler {

    private static final Logger log = LoggerFactory.getLogger(PostsChangeHandler.class);

    @Autowired
    private ResultRepo repo;

//    @StreamListener("inboundPostsChanges")
    public void loggerSink(ChangeModel change) {
        log.info("### RECEIVED KAFKA MESSAGE: {}", change);
        Iterable<Result> rsl = repo.findAll();
        for (Result r : rsl) {
            String[] id = r.getId().split("/");
            String searchSource = "";
            if ("name".equals(id[0])) {
                searchSource = change.getName();
            } else if ("description".equals(id[0])) {
                searchSource = change.getDescription();
            }
            if (searchSource.contains(id[1])) {
                log.info("### CLEARING REDIS CACHE: {}", change);
                repo.delete(r);
            }
        }
    }
}