package task.management.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import task.management.dto.label.LabelCreateRequestDto;
import task.management.dto.label.LabelDto;

public interface LabelService {
    Page<LabelDto> getLabels(Pageable pageable);

    LabelDto createLabels(LabelCreateRequestDto createRequestDto);

    LabelDto updateLabel(Long id, LabelCreateRequestDto createRequestDto);

    void deleteLabel(Long id);
}
