package com.example.ktp.controller;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.service.KtpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ktp")
@AllArgsConstructor
public class KtpController {

    private KtpService ktpService;

    // Build Add KTP REST API
    @PostMapping
    public ResponseEntity<KtpDto> createKtp(@Valid @RequestBody KtpDto ktpDto) {
        KtpDto savedKtp = ktpService.createKtp(ktpDto);
        return new ResponseEntity<>(savedKtp, HttpStatus.CREATED);
    }

    // Build Get KTP by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<KtpDto> getKtpById(@PathVariable("id") Integer ktpId) {
        KtpDto ktpDto = ktpService.getKtpById(ktpId);
        return ResponseEntity.ok(ktpDto);
    }

    // Build Get All KTP REST API
    @GetMapping
    public ResponseEntity<List<KtpDto>> getAllKtp() {
        List<KtpDto> ktps = ktpService.getAllKtp();
        return ResponseEntity.ok(ktps);
    }

    // Build Update KTP REST API
    @PutMapping("{id}")
    public ResponseEntity<KtpDto> updateKtp(@PathVariable("id") Integer ktpId, @Valid @RequestBody KtpDto updatedKtp) {
        KtpDto ktpDto = ktpService.updateKtp(ktpId, updatedKtp);
        return ResponseEntity.ok(ktpDto);
    }

    // Build Delete KTP REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKtp(@PathVariable("id") Integer ktpId) {
        ktpService.deleteKtp(ktpId);
        return ResponseEntity.ok("KTP data deleted successfully");
    }
}
