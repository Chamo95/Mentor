package com.MentorMate.performancereports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.gson.Gson;

public class BusinessLogic {

	private Reader 	reader = null;
	private FileWriter myWriter = null;
	private Gson gson = new Gson();
	private double score[];
	private int X;
	private double percent;
	private String pathData;
	private String pathReport;
	private int move;

	public void setPathData(String pathData) {
		this.pathData = pathData;
	}

	public void setPathReport(String pathReport) {
		this.pathReport = pathReport;
	}

	public void readingJSON() { 
		
		try {
			
			reader = Files.newBufferedReader(Paths.get(pathData));
			Data[] data = gson.fromJson(reader, Data[].class);
			reader = Files.newBufferedReader(Paths.get(pathReport));
			Report report = gson.fromJson(reader, Report.class);
			if (data != null) {
				if (report != null) {
					if (report.getTopPerformersThreshold() < 1 || report.getTopPerformersThreshold() > 100) {
						throw new IllegalArgumentException(
								String.format("topPerformersThreshold from Report.json should be between 1-100"));
					}

				WritetoCSV(calculate(data,report));	 

				} else {
					throw new NullPointerException("Report file is empty");
				}
			} else {
			throw new NullPointerException("Data file is empty");
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			
			e.printStackTrace();
		}
		
	finally {
			try {
				reader.close();
			} catch (Exception e) {
				System.out.println("Illegal Path or Filename");
				e.printStackTrace();
			}
		}

	}

	public Data[] calculate(Data[] data,Report report) {
		
		score = new double[data.length];
		
		for (int i = 0; i < data.length; i++) {
			
			if (data[i].getSalesPeriod() <= report.getPeriodLimit()) {
				
				if (report.isUseExprienceMultiplier() == true) {
					score[i] = (data[i].getTotalSales() / data[i].getSalesPeriod()) * data[i].getExperienceMultiplier();
					data[i].setScore(score[i]);
					
				} else {
					score[i] = data[i].getTotalSales() / data[i].getSalesPeriod();
					data[i].setScore(score[i]);
				}

			}

		}
		Arrays.sort(data);

		percent = score.length * (double) report.getTopPerformersThreshold() / 100;

		if (percent > 0 && percent < 1 && score.length >= 1) {
			X = 1;
		} else {
			X = (int) Math.floor(percent);

			
		}
		 
		return data;
	}

	public void WritetoCSV(Data[] data) {
		
		File file = new File("Report_results.csv");
		
		move = (score.length - X) - 1;
		
		try {
			myWriter = new FileWriter(file);
			myWriter.write("Name , Score");
			myWriter.write(System.lineSeparator());
			
			for (int i = score.length - 1; i >= score.length - X; i--) {
				myWriter.write(data[i].getName() + ", " + data[i].getScore());

				myWriter.write(System.lineSeparator());

			}
			while (move >= 0 && data[score.length - X].getScore() == data[move].getScore()) {
				myWriter.write(data[move].getName() + ", " + data[move].getScore());

				myWriter.write(System.lineSeparator());
				move--;

			}
			System.out.println("Done Writing to CSV file");
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		finally {
			try {
				myWriter.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}