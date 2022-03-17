package com.example.maktab_darsliklari_rest.controller;



import com.example.maktab_darsliklari_rest.dto.ApiResponse;
import com.example.maktab_darsliklari_rest.dto.BookDTO;
import com.example.maktab_darsliklari_rest.dto.ClassDTO;
import com.example.maktab_darsliklari_rest.service.BookService;
import com.example.maktab_darsliklari_rest.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/class")
@RequiredArgsConstructor
public class ClassController {
    final ClassService classService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse all = classService.getAll();
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse all = classService.getOne(id);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(all);
    }

    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody ClassDTO dto) {
        ApiResponse all = classService.save(dto);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(all);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody ClassDTO dto) {
        ApiResponse all = classService.edit(id, dto);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(all);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse all = classService.delete(id);
        return ResponseEntity.status(all.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }
}
