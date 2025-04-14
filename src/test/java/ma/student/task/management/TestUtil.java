package ma.student.task.management;

import ma.student.task.management.dto.project.ProjectCreateRequestDto;
import ma.student.task.management.dto.project.ProjectDto;
import ma.student.task.management.dto.task.TaskCreateRequestDto;
import ma.student.task.management.dto.task.TaskDto;
import ma.student.task.management.dto.user.UserUpdateRequestDto;
import ma.student.task.management.model.Project;
import ma.student.task.management.model.Status;
import ma.student.task.management.model.Task;
import ma.student.task.management.model.User;
import org.h2.engine.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class TestUtil {

    public static Project getProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate(LocalDate.of(2024, 1, 1));
        project.setEndDate(LocalDate.of(2024, 12, 31));
        project.setStatus(Status.IN_PROGRESS);
        project.setDeleted(false);
        return project;
    }

    public static ProjectCreateRequestDto getProjectCreateRequestDto() {
        ProjectCreateRequestDto dto = new ProjectCreateRequestDto();
        dto.setName("Test Project");
        dto.setDescription("Test Description");
        dto.setStartDate(LocalDate.of(2024, 1, 1));
        dto.setEndDate(LocalDate.of(2024, 12, 31));
        dto.setStatus(Status.IN_PROGRESS);
        return dto;
    }

    public static ProjectDto getProjectDto() {
        return new ProjectDto(
                1L,
                "Test Project",
                "Test Description",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 31),
                Status.IN_PROGRESS,
                List.of()
        );
    }

    public static TaskDto getTaskDto() {
        return new TaskDto(1L,
                "task",
                "description",
                "LOW",
                "IN_PROGRESS",
                LocalDate.of(2025, 1, 1),
                1L, 1L);
    }

    public static Task getTask() {
        Task task = new Task();
        task.setName("Task");
        task.setDescription("description");
        task.setPriority(Task.Priority.HIGH);
        task.setStatus(Status.IN_PROGRESS);
        task.setDueDate(LocalDate.of(2025, 6, 1));
        task.setProject(getProject());
        task.setAssignee(getUser());
        return task;
    }

    public static User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user");
        user.setPassword("123");
        user.setEmail("user@gmail.com");
        user.setRoles(new HashSet<>(Role.USER));
        user.setFirstName("user");
        user.setLastName("user");
        return user;
    }

    public static TaskCreateRequestDto getTaskCreateRequestDto() {
        TaskCreateRequestDto requestDto = new TaskCreateRequestDto();
        requestDto.setName("New Task");
        requestDto.setDescription("New description");
        requestDto.setPriority(Task.Priority.HIGH);
        requestDto.setStatus(Status.IN_PROGRESS);
        requestDto.setDueDate(LocalDate.of(2025, 6, 1));
        requestDto.setProjectId(1L);
        requestDto.setAssigneeId(1L);
        return requestDto;
    }

    public static UserUpdateRequestDto getUserUpdateRequestDto() {
        UserUpdateRequestDto requestDto = new UserUpdateRequestDto();
        requestDto.setEmail("updated@email.com");
        requestDto.setUsername("newusername");
        requestDto.setPassword("newStrongPassword123");
        requestDto.setRepeatPassword("newStrongPassword123");
        requestDto.setFirstName("Updated");
        requestDto.setLastName("Name");
        return requestDto;
    }
}
