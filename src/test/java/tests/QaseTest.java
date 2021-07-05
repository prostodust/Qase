package tests;

import adapters.BaseAdapter;
import adapters.ProjectsAdapter;
import adapters.SuiteAdapter;
import com.google.gson.Gson;
import objects.Project;
import objects.ProjectCreated;
import objects.SuiteCreated;
import objects.TestSuite;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomValueGenerator;

public class QaseTest {

    @Test
    public void getAllProjectsTest() {
        String allProject = new BaseAdapter().get("project");
        Assert.assertNotNull(allProject);
    }

    @Test
    public void createProjectTest() {
        String projectCode = "QAOK" + RandomValueGenerator.getRandomChar();
        Project project = Project.builder()
                .title("QA05_" + projectCode)
                .code(projectCode)
                .description("OK Test project")
                .access("all")
                .group(null)
                .build();
        String body = new ProjectsAdapter().create(project);
        ProjectCreated createdProject = new Gson().fromJson(body, ProjectCreated.class);
        Assert.assertEquals(createdProject.getResult().getCode(), projectCode);
    }

    @Test
    public void createNewTestSuiteTest() {
        TestSuite testSuite = TestSuite.builder()
                .title("Test Suite")
                .parentId(null)
                .description("Suite description")
                .preconditions("Preconditions")
                .build();
        String body = new SuiteAdapter().create("QAOK", testSuite);
        SuiteCreated createdSuite = new Gson().fromJson(body, SuiteCreated.class);
        Assert.assertTrue(createdSuite.isStatus());
    }

    @Test
    public void createNewTestSuiteAndDeleteTest() {
        TestSuite testSuite = TestSuite.builder()
                .title("Test Suite 2")
                .parentId(null)
                .description("Suite description 2")
                .preconditions("Preconditions")
                .build();
        String body = new SuiteAdapter().create("QAOK", testSuite);
        SuiteCreated createdSuite = new Gson().fromJson(body, SuiteCreated.class);
        boolean actualDeletedSuiteStatus = new SuiteAdapter().delete("QAOK", createdSuite.getResult().getId());
        Assert.assertTrue(actualDeletedSuiteStatus);
    }
}
