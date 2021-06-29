package tests;

import adapters.BaseAdapter;
import adapters.ProjectsAdapter;
import adapters.SuiteAdapter;
import objects.Project;
import objects.TestSuite;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class QaseTest {

    @Test
    public void getAllProjectsTest() {
        String allProject = new BaseAdapter().get("project");
        Assert.assertNotNull(allProject);
    }

    @Test
    public void createProjectTest() {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'A');
        String projectCode = "QAOK" + c;
        Project project = Project.builder()
                .title("QA05_" + projectCode)
                .code(projectCode)
                .description("OK Test project")
                .access("all")
                .group(null)
                .build();
        String createdProjectCode = new ProjectsAdapter().create(project);
        Assert.assertEquals(createdProjectCode, projectCode);
    }

    @Test
    public void createNewTestSuiteTest() {
        TestSuite testSuite = TestSuite.builder()
                .title("Test Suite")
                .parentId(null)
                .description("Suite description")
                .preconditions("Preconditions")
                .build();
        boolean createdTestSuiteStatus = new SuiteAdapter().createStatus("QAOK", testSuite);
        Assert.assertTrue(createdTestSuiteStatus);
    }

    @Test
    public void createNewTestSuiteAndDeleteTest() {
        TestSuite testSuite = TestSuite.builder()
                .title("Test Suite 2")
                .parentId(null)
                .description("Suite description 2")
                .preconditions("Preconditions")
                .build();
        int createdTestSuiteId = new SuiteAdapter().createId("QAOK", testSuite);
        boolean actualDeletedSuiteStatus = new SuiteAdapter().delete("QAOK", createdTestSuiteId);
        Assert.assertTrue(actualDeletedSuiteStatus);
    }
}