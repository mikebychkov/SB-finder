package org.mike.finder.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mike.finder.model.Post;
import org.mike.finder.model.Result;
import org.mike.finder.repo.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ResultSvc {

    private static final Logger log = LogManager.getLogger(ResultSvc.class);

    @Autowired
    private ResultRepo repo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${my.services.posts}")
    private String postsHost;

    public List<Post> getByFilter(String filter, String value) {

        Result result = repo.findById(filter + "/" + value).orElse(null);
        if (result != null) {
            log.info("### FIND IN CACHE: {}/{}", filter, value);
            return result.getPosts();
        }

        List<Post> posts = getFromPostsSvc(filter, value);

        repo.save(
            Result.of(filter, value, posts)
        );

        return posts;
    }

    private List<Post> getFromPostsSvc(String filter, String value) {

        log.info("### EXCHANGING WITH POSTS SVC: {}/{}", filter, value);

        ResponseEntity<List<Post>> response = restTemplate.exchange(
                "http://" + postsHost + ":5001/posts/{filter}/{value}",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Post>>() {},
                filter,
                value
        );

        return response.getBody();
    }
}
