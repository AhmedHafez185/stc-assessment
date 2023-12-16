package com.stc.assessment.service.items;

import com.stc.assessment.DTO.ItemDTO;
import com.stc.assessment.entity.Files;
import com.stc.assessment.entity.Item;
import com.stc.assessment.entity.PermissionGroup;
import com.stc.assessment.repository.FileRepository;
import com.stc.assessment.repository.ItemRepository;
import com.stc.assessment.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public ItemDTO createSpace(ItemDTO itemDTO) throws IOException {
        itemDTO.setType("Space");
        Item item = modelMapper.map(itemDTO, Item.class);
        item.setGroup(new PermissionGroup(itemDTO.getGroupId()));
        String spacePath = FileUtils.getResourcesPath() + File.separator + item.getName();
        item.setName(spacePath);
        itemRepository.save(item);
        FileUtils.createDirectory(spacePath);
        itemDTO.setId(item.getId());
        return itemDTO;
    }

    @Override
    @Transactional
    public ItemDTO createFolder(ItemDTO itemDTO) throws Exception {
        Long spaceId = itemDTO.getSpaceId();
        if (spaceId == null) throw new Exception("You should enter Space");
        Optional<Item> space = itemRepository.findById(spaceId);
        if (space.isPresent()) {
            if (!space.get().getType().equals("Space")) {
                throw new Exception("Invalid Space you entered");
            }
            if (!itemRepository.isHasEditAccess(spaceId, "EDIT")) {
                throw new Exception("You don't has Edit Permission on this Space");
            }
        } else {
            throw new Exception("Space not exists");
        }
        itemDTO.setType("Folder");
        Item item = modelMapper.map(itemDTO, Item.class);
        item.setGroup(new PermissionGroup(itemDTO.getGroupId()));
        String folderPath = space.get().getName() + File.separator + item.getName();
        item.setName(folderPath);
        itemRepository.save(item);
        FileUtils.createDirectory(folderPath);
        itemDTO.setId(item.getId());
        return itemDTO;
    }

    @Override
    @Transactional
    public ItemDTO createFile(ItemDTO itemDTO) throws Exception {
        Long folderId = itemDTO.getFolderId();
        if (folderId == null) throw new Exception("You should enter Folder");
        Optional<Item> folder = itemRepository.findById(folderId);
        if (folder.isPresent()) {
            if (!folder.get().getType().equals("Folder")) {
                throw new Exception("Invalid Folder you entered");
            }
            if (!itemRepository.isHasEditAccess(folderId, "EDIT")) {
                throw new Exception("You don't has Edit Permission on this Folder");
            }
        } else {
            throw new Exception("Space not exists");
        }
        itemDTO.setType("File");
        Item item = modelMapper.map(itemDTO, Item.class);
        item.setGroup(new PermissionGroup(itemDTO.getGroupId()));
        String filePath = folder.get().getName() + File.separator + item.getName();
        item.setName(filePath);
        itemRepository.save(item);
        createFile(item, filePath);
        return itemDTO;
    }

    private void createFile(Item item, String filePath) throws IOException {
        FileUtils.createPdf(filePath,"Hello, this is a sample assessment PDF content.");
        Files files = new Files();
        files.setItem(item);
        files.setBinaryContent(java.nio.file.Files.readAllBytes(new File(filePath).toPath()));
        files.setId(item.getId());
        fileRepository.save(files);
    }
}
