package org.med.youhospital.serverside.controller;

import jakarta.validation.Valid;
import org.med.youhospital.serverside.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class BaseController<Request, Id, Response, Service extends BaseService<Request, Id, Response>> {

    private final Service service;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected BaseController(Service service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Response>> findAllUsers() {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{num}")
    public ResponseEntity<Response> findOneUser(@PathVariable Id num) {
        return ResponseEntity.ok(service.findOne(num));
    }


    @PostMapping
    public ResponseEntity<Response> saveUser(@RequestBody @Valid Request userDto) {
        return ResponseEntity.ok(service.save(userDto));
    }

    @PutMapping("/{num}")
    public ResponseEntity<Response> updateUser(@PathVariable Id num, @RequestBody @Valid Request userDto) {
        return ResponseEntity.ok(service.update(num, userDto));
    }


    @DeleteMapping("/{num}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "num") Id num) {
        service.delete(num);
        return ResponseEntity.noContent().build();
    }
}
