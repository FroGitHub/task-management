package task.management.service.impl;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import task.management.dto.attachment.AttachmentDto;
import task.management.exception.EntityNotFoundException;
import task.management.mapper.AttachmentMapper;
import task.management.model.Attachment;
import task.management.model.Task;
import task.management.repository.AttachmentRepository;
import task.management.repository.TaskRepository;
import task.management.service.AttachmentService;
import task.management.service.DropboxService;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final DropboxService dropboxService;
    private final TaskRepository taskRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    public AttachmentDto uploadAttachment(MultipartFile file,
                                          Long taskId) throws Exception {

        String dropboxFileId = dropboxService.uploadFile(file);
        Attachment attachment = new Attachment();
        attachment.setDropboxFileId(dropboxFileId);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is no task with id: " + taskId));

        task.addAttachment(attachment);
        attachment.setFilename(file.getOriginalFilename());
        attachment.setUploadDate(LocalDateTime.now());

        return attachmentMapper.toDto(attachmentRepository.save(attachment));
    }

    @Override
    public Page<AttachmentDto> getAttachmentsByTask(Pageable pageable, Long taskId) {
        Page<Attachment> attachments = attachmentRepository.findByTaskId(pageable, taskId);
        attachments.stream()
                .map(Attachment::getDropboxFileId)
                .forEach(dropboxService::downloadById);
        return attachments.map(attachmentMapper::toDto);
    }

    @Override
    @Transactional
    public void deleteAttachment(Long id) throws Exception {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                    "There is no attachment with id: " + id));
        dropboxService.deleteFile(attachment.getDropboxFileId());
        attachmentRepository.deleteById(id);
    }
}
