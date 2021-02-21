package org.mike.finder.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mike.finder.model.Post;
import org.mike.finder.model.Result;
import org.mike.finder.repo.ResultRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private final Logger log = LogManager.getLogger(EventConsumer.class);

    @Autowired
    private ResultRepo repo;

    @RabbitListener(queues = "${my.event.queue}")
    public void processToDo(Post post) {
        log.info("### RECEIVED MESSAGE: {}", post);
        Iterable<Result> rsl = repo.findAll();
        for (Result r : rsl) {
            String[] id = r.getId().split("/");
            String searchSource = "";
            if ("name".equals(id[0])) {
                searchSource = post.getName();
            } else if ("description".equals(id[0])) {
                searchSource = post.getDescription();
            }
            if (searchSource.contains(id[1])) {
                log.info("### CLEARING REDIS CACHE: {}", post);
                repo.delete(r);
            }
        }
    }
}
