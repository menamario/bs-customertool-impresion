//package mx.com.bsmexico.customertool.dispersion.test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import mx.com.bsmexico.customertool.api.process.ImportTarget;
//import mx.com.bsmexico.customertool.api.report.ContextReport;
//import mx.com.bsmexico.customertool.api.report.ReportDataSourceFactory;
//import mx.com.bsmexico.customertool.api.report.ReportGenerator;
//import mx.com.bsmexico.customertool.impresion.plugin.DispersionDefinitiva;
//import mx.com.bsmexico.customertool.impresion.plugin.DispersionDefinitivaCSVImporter;
//import mx.com.bsmexico.customertool.impresion.plugin.DispersionDefinitivaPdfExport;
//
//public class ReportTest {
//
//	@Test
//	public void reportTest() {
//		final File file = new File("testDispersionPago.pdf");
//		final List<DispersionDefinitiva> dataReport = new ArrayList<>();
//		final ClassLoader classLoader = getClass().getClassLoader();
//		final File dispersiones = new File(classLoader.getResource("layouts/20180808_11111111111_222.csv").getFile());
//		final DispersionDefinitivaCSVImporter importer = new DispersionDefinitivaCSVImporter(
//				new ImportTarget<DispersionDefinitiva>() {
//
//					@Override
//					public void setData(List<DispersionDefinitiva> data) {
//						dataReport.addAll(data);
//					}
//				});
//		try {
//			importer.importFile(dispersiones);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//
//		try {
//			final FileOutputStream fout = new FileOutputStream(file);
//			file.createNewFile();
//			final ContextReport context = new ContextReport();
//			context.addParameter("cliente", "TEST");
//			final File logo = new File(classLoader.getResource("logoSabadell.jpeg").getFile());
//			final FileInputStream imgStream = new FileInputStream(logo);
//			context.addImageParameter("logo", imgStream);
//			ReportGenerator.generateFromCompiledReport("reports/ComprobanteDispersionPago.jasper", context,
//					ReportDataSourceFactory.getBeanDataSource(dataReport), fout);
//			fout.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void reportExportPdfTest() {
//		final List<DispersionDefinitiva> dataReport = new ArrayList<>();
//		final ClassLoader classLoader = getClass().getClassLoader();
//		final File dispersiones = new File(classLoader.getResource("layouts/20180808_11111111111_222.csv").getFile());
//		final DispersionDefinitivaCSVImporter importer = new DispersionDefinitivaCSVImporter(
//				new ImportTarget<DispersionDefinitiva>() {
//
//					@Override
//					public void setData(List<DispersionDefinitiva> data) {
//						dataReport.addAll(data);
//					}
//				});
//		try {
//			importer.importFile(dispersiones);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//		try {
//			final File directory = new File("/home/jchr/Desktop/temp");
//			final File logo = new File(classLoader.getResource("logoSabadell.jpeg").getFile());			
//			final DispersionDefinitivaPdfExport export = new DispersionDefinitivaPdfExport();
//			export.export(directory, dataReport, logo);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}	
//
//}
