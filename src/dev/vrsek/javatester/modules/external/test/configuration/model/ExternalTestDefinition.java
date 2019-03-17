package dev.vrsek.javatester.modules.external.test.configuration.model;

public class ExternalTestDefinition {
	private String beforeSetup;
	private String beforeTest;
	private String afterTest;
	private String afterTearDown;
	private String testMethodName;

	public String getBeforeSetup() {
		return beforeSetup;
	}

	public void setBeforeSetup(String beforeSetup) {
		this.beforeSetup = beforeSetup;
	}

	public String getBeforeTest() {
		return beforeTest;
	}

	public void setBeforeTest(String beforeTest) {
		this.beforeTest = beforeTest;
	}

	public String getAfterTest() {
		return afterTest;
	}

	public void setAfterTest(String afterTest) {
		this.afterTest = afterTest;
	}

	public String getAfterTearDown() {
		return afterTearDown;
	}

	public void setAfterTearDown(String afterTearDown) {
		this.afterTearDown = afterTearDown;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}
}
