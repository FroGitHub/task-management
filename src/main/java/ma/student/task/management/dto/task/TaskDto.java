package ma.student.task.management.dto.task;

import java.time.LocalDate;

public record TaskDto(Long id,
                      String name,
                      String description,
                      String priority,
                      String status,
                      LocalDate dueDate,
                      Long projectId,
                      Long assigneeId) {
}
