package com.best.of.ngah.bionumtech.services.dons;

import com.best.of.ngah.bionumtech.dtos.dons.DonForm;
import com.best.of.ngah.bionumtech.dtos.dons.DonSummarized;
import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DonService {
    Paginate<List<DonSummarized>> findAllDons(int page, int size, String propertyToSortBy, Sort.Direction direction);

    DonSummarized saveDon(DonForm donForm);

    void deleteDonById(Long id);

    DonSummarized findDonById(Long id);
}
