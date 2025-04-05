package ma.student.task.management.dto.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ma.student.task.management.model.Status;

public record ProjectDto(Long id,
                         String name,
                         String description,
                         LocalDate startDate,
                         LocalDate endDate,
                         Status status,
                         List<Long> taskIds) {
    public ProjectDto {
        if (taskIds == null) {
            taskIds = new ArrayList<>();
        }
    }

}
