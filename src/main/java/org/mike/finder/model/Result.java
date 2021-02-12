package org.mike.finder.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash
@Data
public class Result {

    @Id
    private String id;
    private List<Post> posts;

    public static Result of(String filter, String value, List<Post> posts) {
        Result rsl = new Result();
        rsl.setId(filter + "/" + value);
        rsl.setPosts(posts);
        return rsl;
    }
}
