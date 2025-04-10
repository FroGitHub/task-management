package ma.student.task.management.service;

import ma.student.task.management.dto.project.ProjectCreateRequestDto;
import ma.student.task.management.dto.project.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    Page<ProjectDto> getProjects(Pageable pageable);

    ProjectDto createProject(ProjectCreateRequestDto createRequestDto);

    ProjectDto updateProject(Long id, ProjectCreateRequestDto createRequestDto);

    ProjectDto getProject(Long id);

    void deleteProject(Long id);
}
