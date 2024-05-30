package be.portal.job.controllers;

import be.portal.job.dtos.driving_licence.requests.DrivingLicenceRequest;
import be.portal.job.dtos.driving_licence.responses.DrivingLicenceResponse;
import be.portal.job.services.IDrivingLicenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driving-licences")
@CrossOrigin("*")
@PreAuthorize("hasAnyAuthority('SEEKER', 'ADMIN')")
public class DrivingLicenceController {

    private final IDrivingLicenceService drivingLicenceService;

    @GetMapping
    public ResponseEntity<List<DrivingLicenceResponse>> getAll() {
        return ResponseEntity.ok(drivingLicenceService.getAllBySeeker());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<DrivingLicenceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(drivingLicenceService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DrivingLicenceResponse> add(
            @RequestBody
            @Valid DrivingLicenceRequest drivingLicenceRequest) {
        return ResponseEntity.ok(drivingLicenceService.add(drivingLicenceRequest));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<DrivingLicenceResponse> updateById(
            @PathVariable Long id,
            @RequestBody @Valid DrivingLicenceRequest drivingLicenceRequest) {
        return ResponseEntity.ok(drivingLicenceService.update(id, drivingLicenceRequest));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<DrivingLicenceResponse> delete(
            @PathVariable Long id ) {
        return ResponseEntity.ok(drivingLicenceService.delete(id));
    }
}
