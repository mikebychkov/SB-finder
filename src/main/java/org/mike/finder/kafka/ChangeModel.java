package org.mike.finder.kafka;

import lombok.Data;

@Data
public class ChangeModel {

    private String name;
    private String description;

    public static ChangeModel of(String name, String description) {
        ChangeModel rsl = new ChangeModel();
        rsl.setName(name);
        rsl.setDescription(description);
        return rsl;
    }
}
