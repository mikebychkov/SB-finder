package org.mike.finder.repo;

import org.mike.finder.model.Result;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepo extends CrudRepository<Result, String> {
}
