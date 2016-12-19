package at.medunigraz.imi.abbres.dao;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.stats.Evaluator;

public class ValidationReader implements Closeable, Iterator<Abbreviation> {
	private static final Logger LOG = LoggerFactory.getLogger(Evaluator.class);

	private static final Set<String> OUT_OF_SCOPE = new HashSet<>();
	static {
		OUT_OF_SCOPE.add("OUT OF SCOPE");
		OUT_OF_SCOPE.add("END OF SENTENCE");
		OUT_OF_SCOPE.add("DIGIT");
		OUT_OF_SCOPE.add("PUNCTUATIONS");
		OUT_OF_SCOPE.add("ROMAN NUMBER");
		OUT_OF_SCOPE.add("ERROR");
		OUT_OF_SCOPE.add("DATE");
		OUT_OF_SCOPE.add("SPELLING ERROR");
		OUT_OF_SCOPE.add("PROPER NAME");
		OUT_OF_SCOPE.add("LEXICALIZED");
	}

	// This matches a single slash
	private static final Pattern TOKEN_SEPARATOR = Pattern.compile("\\\\");

	private static final int WINDOW_SIZE = 100;
	private static final int CONTEXT_COLUMN = 0, GOLD_COLUMN = 1, GUESS_COLUMN = 2;

	private File file;

	private XSSFWorkbook workbook;

	private Iterator<Row> rowIterator;

	private Row row = null;

	public ValidationReader(File file) {
		this.file = file;
		openSheet();
	}

	private void openSheet() {
		try {
			LOG.info("Opening sheet " + file.getAbsolutePath());
			workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write a guess into the appropriate column of the current row.
	 * 
	 * @param guess
	 */
	public void writeGuess(String guess) {
		Cell cell = row.getCell(GUESS_COLUMN);
		if (cell == null) {
			cell = row.createCell(GUESS_COLUMN, CellType.STRING);
		}
		cell.setCellValue(guess);
	}

	/**
	 * 
	 * @return
	 */
	public int getRowNum() {
		return row.getRowNum() + 1;
	}

	public void writeGuess(Abbreviation abbreviation) {
		writeGuess(abbreviation.getExpansion());
	}

	private boolean isOutOfScope(String s) {
		if (OUT_OF_SCOPE.contains(s)) {
			return true;
		}
		return false;
	}

	@Override
	public void close() {
		try {
			workbook.write(new FileOutputStream(file));
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() {
		return rowIterator.hasNext();
	}

	@Override
	public Abbreviation next() {
		row = rowIterator.next();

		Cell cell = row.getCell(GOLD_COLUMN);
		if (cell == null) {
			return null;
		}

		String expansion = cell.getStringCellValue();

		if (expansion.isEmpty()) {
			LOG.debug("Empty expansion at row number " + row.getRowNum());
			return null;
		}

		if (isOutOfScope(expansion)) {
			return null;
		}

		String sourceText = row.getCell(CONTEXT_COLUMN).getStringCellValue();

		// Sanity check
		if (sourceText.charAt(WINDOW_SIZE / 2) != TextUtils.ABBREVIATION_MARK) {
			LOG.debug("Unexpected abbreviation marker position at row number " + row.getRowNum());
			return null;
		}

		// Changes all non-default token separators to a default one.
		sourceText = TOKEN_SEPARATOR.matcher(sourceText).replaceAll(String.valueOf(TextUtils.DEFAULT_TOKEN_SEPARATOR));

		int lastSeparator = sourceText.lastIndexOf(TextUtils.DEFAULT_TOKEN_SEPARATOR, WINDOW_SIZE / 2);
		String token = sourceText.substring(lastSeparator + 1, WINDOW_SIZE / 2 + 1);

		String leftString = sourceText.substring(0, lastSeparator);
		String rightString = sourceText.substring(WINDOW_SIZE / 2 + 1, sourceText.length());

		return new Abbreviation(token).withExpansion(expansion).withLeftContext(leftString)
				.withRightContext(rightString);
	}

}
