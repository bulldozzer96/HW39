package com.ua.rd.HW39.rest;



import com.ua.rd.HW39.domain.Student;
import com.ua.rd.HW39.service.TestStudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequiredArgsConstructor
public class TestStudentController {

    private final TestStudentService testStudentService;

    @GetMapping("/test_students")
    public Optional<ResponseEntity<List<Student>>> findAll() {
        return Optional.ofNullable(ResponseEntity.ok(testStudentService.findAll()));
    }
    @GetMapping("/test_students/{groupId}")
    public Optional<ResponseEntity<List<Student>>> findByGroupId(@PathVariable int groupId) {

        return Optional.ofNullable(ResponseEntity.ok(testStudentService.findByGroupId(groupId)));

    }

}