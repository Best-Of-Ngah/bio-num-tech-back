package com.best.of.ngah.bionumtech.services.dons;

import com.best.of.ngah.bionumtech.dtos.dons.DonForm;
import com.best.of.ngah.bionumtech.dtos.dons.DonSummarized;
import com.best.of.ngah.bionumtech.dtos.pagination.PageInfo;
import com.best.of.ngah.bionumtech.dtos.pagination.Paginate;
import com.best.of.ngah.bionumtech.entities.Don;
import com.best.of.ngah.bionumtech.exceptions.HttpNotFoundException;
import com.best.of.ngah.bionumtech.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class DonServiceImpl implements DonService {
    private final RepositoryFactory repository;

    @Override
    public Paginate<List<DonSummarized>> findAllDons(int page, int size, String propertyToSortBy, Sort.Direction direction) {
        var pageable = PageRequest.of(page - 1, size, Sort.by(direction, propertyToSortBy));
        var donPage = repository.getDonRepository().findAll(pageable);
        var donSummaries = donPage.getContent().stream()
                .map(this::toDonSummarized)
                .collect(Collectors.toList());
        var pageInfo = new PageInfo(donPage.hasNext(), donPage.hasPrevious());
        return new Paginate<>(donSummaries, pageInfo, donPage.getTotalPages());
    }

    @Transactional
    @Override
    public DonSummarized saveDon(DonForm donForm) {
        var donRepository = repository.getDonRepository();
        var project = repository.getProjectRepository()
                .findById(donForm.getProjectId())
                .orElseThrow(() -> new HttpNotFoundException("Product not found"));
        var user = repository.getUserRepository().findById(donForm.getUserId())
                .orElseThrow(() -> new HttpNotFoundException("User not found"));

        Don don = Don.builder().build();
        if (donForm.getId() != null) {
            var optDon = donRepository.findById(donForm.getId());

            if (optDon.isPresent()) {
                don = optDon.get();
            }
        }

        don.setProvenance(donForm.getProvenance());
        don.setProvenanceDate(LocalDate.from(donForm.getProvenanceDate()));
        don.setAmount(donForm.getAmount());
        don.setUser(user);
        don.setProject(project);
        Don savedDon = donRepository.save(don);
        return toDonSummarized(savedDon);
    }


    @Override
    public void deleteDonById(Long id) {
        var repo = repository.getDonRepository();
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return;
        }
        throw new HttpNotFoundException("Don not found");
    }


    @Override
    public DonSummarized findDonById(Long id) {
        var optionalDon = repository.getDonRepository().findById(id);
        return optionalDon.map(this::toDonSummarized).orElseThrow(() -> new HttpNotFoundException("Don not found !"));
    }


    private DonSummarized toDonSummarized(Don don) {
        return new DonSummarized(
                don.getId(),
                don.getProvenance(),
                don.getProvenanceDate(),
                don.getAmount(),
                don.getUser().getId(),
                don.getProject().getId(),
                don.getCreatedAt(),
                don.getUpdatedAt()
        );
    }

}
