package com.stc.assessment.controller;

import com.stc.assessment.entity.Item;
import com.stc.assessment.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileMetadataController {
    @Autowired
    private ItemRepository itemRepository;

    @QueryMapping
    Iterable<Item> items() {
        return itemRepository.findAll();
    }
    @QueryMapping
    Optional<Item> itemById(@Argument Long id) {
        return itemRepository.findById(id);
    }

}
