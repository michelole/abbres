package at.medunigraz.imi.abbres.dao;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.context.LeftContext;
import at.medunigraz.imi.abbres.model.context.RightContext;

public class ValidationReader implements Closeable, Iterator<Abbreviation> {
	private File file;

	private static final String OUT_OF_SCOPE = "OUT OF SCOPE";
	private static final char ABBREVIATION_MARK = '.';
	private static final String TOKEN_SEPARATOR = " ";

	private static final int WINDOW_SIZE = 100;

	private XSSFWorkbook workbook;

	private Iterator<Row> rowIterator;

	public ValidationReader(File file) {
		this.file = file;
		openSheet();
	}

	private void openSheet() {
		try {
			workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		workbook.close();
	}

	@Override
	public boolean hasNext() {
		return rowIterator.hasNext();
	}

	@Override
	public Abbreviation next() {
		Row row = rowIterator.next();

		Cell cell = row.getCell(1);
		if (cell == null) {
			return null;
		}

		String expansion = cell.getStringCellValue();

		if (expansion.isEmpty()) {
			return null;
		}

		if (expansion.equals(OUT_OF_SCOPE)) {
			return null;
		}

		String sourceText = row.getCell(0).getStringCellValue();

		// Sanity check
		if (sourceText.charAt(WINDOW_SIZE / 2) != ABBREVIATION_MARK) {
			return null;
		}

		int lastSeparator = sourceText.lastIndexOf(TOKEN_SEPARATOR, WINDOW_SIZE / 2);
		String token = sourceText.substring(lastSeparator + 1, WINDOW_SIZE / 2 + 1);

		String leftString = sourceText.substring(0, lastSeparator).trim();
		LeftContext leftContext = new LeftContext(leftString);
		
		String rightString = sourceText.substring(WINDOW_SIZE / 2 + 1, sourceText.length()).trim();
		RightContext rightContext = new RightContext(rightString);

		return new Abbreviation(token).withExpansion(expansion).withLeftContext(leftContext)
				.withRightContext(rightContext);
	}
}
