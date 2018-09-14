package mx.com.bsmexico.customertool.impresion.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ibm.icu.text.SimpleDateFormat;

import mx.com.bsmexico.customertool.api.report.ContextReport;
import mx.com.bsmexico.customertool.api.report.ReportDataSourceFactory;
import mx.com.bsmexico.customertool.api.report.ReportGenerator;

/**
 * @author jchr
 *
 */
public class DispersionDefinitivaPdfExport {

	private boolean singleDocument;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

	public void export(final File directory, final List<DispersionDefinitiva> data, final String logo) throws Exception {
		if (directory == null) {
			throw new IllegalArgumentException("The directory can not be null");
		}
		if (!directory.exists() || !directory.isDirectory()) {
			throw new IllegalArgumentException("The directory not exist or is a file");
		}
		if (!directory.canWrite()) {
			throw new IllegalArgumentException("Permission denied to write");
		}
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException("Data can not be null or empty");
		}

		final ContextReport context = new ContextReport();
		context.addImageParameter("logo", getClass().getResourceAsStream(logo));
		context.addParameter("cliente", "");
		if (singleDocument) {
			final File file = File.createTempFile("DPTmp", UUID.randomUUID().toString() + ".pdf", directory);
			final FileOutputStream fout = new FileOutputStream(file);
			ReportGenerator.generateFromCompiledReport("reports/ComprobanteDispersionPago.jasper", context,
					ReportDataSourceFactory.getBeanDataSource(data), fout);
			fout.flush();
			fout.close();
		} else {
			File file = null;
			FileOutputStream fout = null;			
			for (DispersionDefinitiva d : data) {
				context.addImageParameter("logo", getClass().getResourceAsStream(logo));
				file = new File(generateFileName(directory, d.getReferencia(), d.getImporte()));
				file.createNewFile();
				fout = new FileOutputStream(file);
				ReportGenerator.generateFromCompiledReport("reports/ComprobanteDispersionPago.jasper", context,
						ReportDataSourceFactory.getBeanDataSource(new DispersionDefinitiva[] { d }), fout);
				fout.flush();
				fout.close();
			}
		}
	}

	/**
	 * @param singleDocument
	 */
	public void setSingleDocument(final boolean singleDocument) {
		this.singleDocument = singleDocument;
	}

	/**
	 * @return
	 */
	public boolean isSingleDocument() {
		return this.singleDocument;
	}

	/**
	 * @param directory
	 * @param reference
	 * @param amount
	 * @return
	 */
	private String generateFileName(final File directory, final String reference, final String amount) {
		StringBuffer buffer = new StringBuffer(directory.getAbsolutePath());
		buffer.append("/");
		buffer.append(sdf.format(new Date()));
		buffer.append(reference);
		buffer.append("_");
		buffer.append(amount);
		buffer.append(".pdf");
		return buffer.toString();
	}

}
