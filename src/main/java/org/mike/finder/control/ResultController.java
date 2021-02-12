package org.mike.finder.control;

import org.mike.finder.model.Post;
import org.mike.finder.service.ResultSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultController {

    @Autowired
    private ResultSvc svc;

    @GetMapping("/find/{filter}/{value}")
    public List<Post> getByFilter(@PathVariable String filter, @PathVariable String value) {
        return svc.getByFilter(filter, value);
    }
}
