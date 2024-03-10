package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Department;
import org.med.youhospital.serverside.model.entity.Room;
import org.med.youhospital.serverside.model.request.RoomReq;
import org.med.youhospital.serverside.model.response.RoomRes;
import org.med.youhospital.serverside.repository.DepartmentRepository;
import org.med.youhospital.serverside.repository.RoomRepository;
import org.med.youhospital.serverside.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RoomRes save(RoomReq roomReq) {
        Department department = departmentRepository.findById(roomReq.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department Not found"));
        Room room = modelMapper.map(roomReq, Room.class);
        room.setDepartment(department);
        return modelMapper.map(roomRepository.save(room), RoomRes.class);
    }

    @Override
    public List<RoomRes> findAll() {
        return roomRepository.findAll().stream().map(Room -> modelMapper.map(Room, RoomRes.class)).collect(Collectors.toList());
    }

    @Override
    public RoomRes findOne(UUID id) {
        return roomRepository.findById(id)
                .map(Room -> modelMapper.map(Room, RoomRes.class)).orElseThrow(() -> new ResourceNotFoundException("Room Not found with this: " + id));
    }

    @Override
    public RoomRes update(UUID id, RoomReq roomReq) {
        Room existingRoom = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room Not found with this: " + id));
        Department department = departmentRepository.findById(roomReq.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department Not found"));
        BeanUtils.copyProperties(roomReq, existingRoom);
        existingRoom.setId(id);
        existingRoom.setDepartment(department);
        return modelMapper.map(roomRepository.save(existingRoom), RoomRes.class);
    }

    @Override
    public void delete(UUID id) {
        roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room Not found with this: " + id));
        roomRepository.deleteById(id);
    }

}
