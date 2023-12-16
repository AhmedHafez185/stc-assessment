package com.stc.assessment.service.items;

import com.stc.assessment.DTO.ItemDTO;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface ItemService {
    public ItemDTO createSpace(ItemDTO itemDTO) throws IOException;
    public ItemDTO createFolder(ItemDTO itemDTO) throws Exception;
    public ItemDTO createFile(ItemDTO itemDTO) throws Exception;
}
