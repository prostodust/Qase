package adapters;

import objects.TestSuite;

public class SuiteAdapter extends BaseAdapter {
    private static final String SUITE_URI = "suite/";

    public String create(String projectCode, TestSuite testSuite) {
        return post(SUITE_URI + projectCode, converter.toJson(testSuite));
    }

    public boolean delete(String projectCode, int testSuiteId) {
        return delete(SUITE_URI + projectCode + "/" + testSuiteId).body().path("status");
    }
}