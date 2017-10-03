package com.kodilla.hibernate.tasklist;

import com.kodilla.hibernate.tasklist.dao.TaskListDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListDaoTestSuite {

    @Autowired
    private TaskListDao taskListDao;
    private static final String LISTNAME = "Test list";
    private static final String DESCRIPTION = "Ipsum lorem";


    @Test
    public void testTaskDaoSave() {
        //Given
        TaskList taskList = new TaskList(LISTNAME, DESCRIPTION);

        //When
        taskListDao.save(taskList);


        //Then
        int id = taskList.getId();
        TaskList readTask = taskListDao.findOne(id);
        Assert.assertEquals(id, readTask.getId());

        //CleanUp
        taskListDao.delete(id);
    }

    @Test
    public void testFindByListName() {
        //given
        TaskList taskList = new TaskList(LISTNAME, DESCRIPTION);
        taskListDao.save(taskList);
        String name = taskList.getListName();

        //when
        List<TaskList> readTaskListName = taskListDao.findByListName(name);

        //then
        Assert.assertEquals(1, readTaskListName.size());

        //cleanup
        int id = readTaskListName.get(0).getId();
        taskListDao.delete(id);

    }
}
