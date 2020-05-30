package com.todo.app.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.app.entity.Todo;
import com.todo.app.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class TodoExpirationJob implements Job {

    private final TodoRepository todoRepository;
    private final ObjectMapper objectMapper;
    private final String EXPIRED_PATH_NAME = "expired";

    public TodoExpirationJob(TodoRepository todoRepository, ObjectMapper objectMapper) {
        this.todoRepository = todoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(JobExecutionContext context) {
        UUID id = (UUID) context.getTrigger().getJobDataMap().get("id");
        Optional<Todo> repository = todoRepository.findById(id);
        repository.ifPresent(this::saveExpiredTask);
    }

    private void saveExpiredTask(Todo todo) {
        createPathIfDoesNotExist();
        try {
            String fileName = todo.getId().toString();
            File file = new File(EXPIRED_PATH_NAME, fileName);
            file.createNewFile();
            objectMapper.writeValue(file, todo);
        } catch (Exception ex) {
            log.error("Something went wrong while saving expired todo", ex);
        }
    }

    private void createPathIfDoesNotExist() {
        File file = new File(EXPIRED_PATH_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
