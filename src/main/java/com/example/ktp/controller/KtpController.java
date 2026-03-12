package com.example.ktp.controller;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.service.KtpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling KTP data REST API requests.
 */
@RestController
@RequestMapping("/api/ktp")
@AllArgsConstructor
public class KtpController {

    private KtpService ktpService;

    // Build Add KTP REST API
    /**
     * Creates a new KTP record.
     * @param ktpDto the KTP data to save
     * @return the created KTP data
     */
    @PostMapping
    public ResponseEntity<KtpDto> createKtp(@Valid @RequestBody KtpDto ktpDto) {
        KtpDto savedKtp = ktpService.createKtp(ktpDto);
        return new ResponseEntity<>(savedKtp, HttpStatus.CREATED);
    }

    // Build Get KTP by ID REST API
    /**
     * Retrieves a KTP record by its ID.
     * @param ktpId the ID of the KTP to retrieve
     * @return the KTP data if found
     */
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
    /**
     * Updates an existing KTP record.
     * @param ktpId the ID of the KTP to update
     * @param updatedKtp the new data
     * @return the updated KTP data
     */
    @PutMapping("{id}")
    public ResponseEntity<KtpDto> updateKtp(@PathVariable("id") Integer ktpId, @Valid @RequestBody KtpDto updatedKtp) {
        KtpDto ktpDto = ktpService.updateKtp(ktpId, updatedKtp);
        return ResponseEntity.ok(ktpDto);
    }

    // Build Delete KTP REST API
    /**
     * Deletes a KTP record by its ID.
     * @param ktpId the ID of the KTP to delete
     * @return success message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKtp(@PathVariable("id") Integer ktpId) {
        ktpService.deleteKtp(ktpId);
        return ResponseEntity.ok("KTP data deleted successfully");
    }
}
