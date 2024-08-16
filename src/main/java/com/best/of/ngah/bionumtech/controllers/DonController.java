package com.best.of.ngah.bionumtech.controllers;

import com.best.of.ngah.bionumtech.dtos.dons.DonForm;
import com.best.of.ngah.bionumtech.dtos.dons.DonSummarized;
import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.services.dons.DonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/dons")
@RequiredArgsConstructor
public class DonController {
    private final DonService donService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Paginate<List<DonSummarized>> getAllProject(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "provenanceDate") String propertyToSortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return donService.findAllDons(page, size, propertyToSortBy, Sort.Direction.valueOf(direction.toUpperCase()));
    }

    @PostMapping
    public DonSummarized saveDon(@RequestBody @Valid DonForm donForm) {
        return donService.saveDon(donForm);
    }

    @GetMapping("/{id}")
    public DonSummarized getOneDon(@PathVariable Long id) {
        return donService.findDonById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteDon(@PathVariable Long id) {
        donService.deleteDonById(id);
    }

}
