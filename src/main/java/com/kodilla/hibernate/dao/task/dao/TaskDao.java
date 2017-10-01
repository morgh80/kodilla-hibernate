package com.kodilla.hibernate.dao.task.dao;

import com.kodilla.hibernate.dao.task.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskDao extends CrudRepository<Task, Integer> {
List<Task> findByDuration(int duration);
}