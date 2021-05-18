package com.MentorMate.performancereports_Junit_Test;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.MentorMate.performancereports.BusinessLogic;
import com.MentorMate.performancereports.Data;
import com.MentorMate.performancereports.Report;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//JUnit test
public class AppTest {

	static BusinessLogic logic;
	static Data data;
	static Data[] dataArray = new Data[1];
	static Report report = new Report();
	static Reader reader;
	static String text;
	static JsonElement jelement;
	static JsonObject json;

	@BeforeAll
	public static void init() {
		report = new Report();
		data = new Data();
		logic = new BusinessLogic();
		data.setExperienceMultiplier(0.5);
		data.setName("testname");
		data.setSalesPeriod(9);
		data.setScore(10);
		data.setTotalSales(300);
		dataArray[0] = data;
		report.setPeriodLimit(10);
		report.setTopPerformersThreshold(10);
		report.setUseExprienceMultiplier(true);

	}

	@Test
	public void testAllAttributesJSON() throws IOException {

		JsonParser jsonParser = new JsonParser();
		JsonArray arr = (JsonArray) jsonParser.parse(new FileReader("New.json"));

		assertTrue(((JsonObject) arr.get(0)).has("name"));
		assertTrue(((JsonObject) arr.get(0)).has("totalSales"));
		assertTrue(((JsonObject) arr.get(0)).has("salesPeriod"));
		assertTrue(((JsonObject) arr.get(0)).has("experienceMultiplier"));

		text = new String(Files.readAllBytes(Paths.get("Report.json")), StandardCharsets.UTF_8);
		jelement = new JsonParser().parse(text);
		json = jelement.getAsJsonObject();
		assertTrue(json.has("topPerformersThreshold"));
		assertTrue(json.has("useExprienceMultiplier"));
		assertTrue(json.has("periodLimit"));

	}

	@Test
	public void testRightValuesCSV() throws IOException {
		logic.WritetoCSV(logic.calculate(dataArray, report));
		ArrayList<String> ar = new ArrayList<String>();

		Scanner sc = new Scanner(new File("Report_results.csv"));
		sc.useDelimiter(",|\\r\\n");
		while (sc.hasNext()) {

			ar.add(sc.next().replace(" ", ""));

		}
		sc.close();

		assertTrue(ar.get(0).equals("Name"));
		assertTrue(ar.get(1).equals("Score"));
		assertTrue(ar.get(2).equals(data.getName()));
		

	}

}
