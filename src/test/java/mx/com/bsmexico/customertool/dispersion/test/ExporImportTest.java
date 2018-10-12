package mx.com.bsmexico.customertool.dispersion.test;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import mx.com.bsmexico.customertool.api.process.ImportTarget;
import mx.com.bsmexico.customertool.impresion.plugin.DispersionDefinitiva;
import mx.com.bsmexico.customertool.impresion.plugin.DispersionDefinitivaCSVImporter;
import mx.com.bsmexico.customertool.impresion.plugin.DispersionDefinitivaTXTImporter;
import mx.com.bsmexico.customertool.impresion.plugin.DispersionDefinitivaValidator;

public class ExporImportTest {

	@Test
	public void ImportDsipersionDefinitivaCSVTest() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layouts/20180808_11111111111_222_D.csv").getFile());
		final DispersionDefinitivaCSVImporter importer = new DispersionDefinitivaCSVImporter(
				new ImportTarget<DispersionDefinitiva>() {

					@Override
					public void setData(List<DispersionDefinitiva> data) {
						Assert.assertNotNull(data);
						Assert.assertTrue(data.size() == 8);						
						Assert.assertTrue("0".equals(data.get(0).getTipoMovimiento()));
						Assert.assertTrue("H".equals(data.get(0).getAplicacion()));
						Assert.assertTrue("20180808134519".equals(data.get(0).getFecha()));
						Assert.assertTrue("00".equals(data.get(0).getTipoTransaccion()));
						Assert.assertTrue("00000211906".equals(data.get(0).getCuentaCargo()));
						Assert.assertTrue("01".equals(data.get(0).getTipoCuentaBeneficiario()));
						Assert.assertTrue("00000129001".equals(data.get(0).getCuentaAbono()));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getBanco())));						
						Assert.assertTrue("PF".equals(data.get(0).getTipoPersona()));
						Assert.assertTrue("OSCAR YAIR SANCHEZ SALAZAR".equals(data.get(0).getNombre()));
						Assert.assertTrue("MAEY760113T85".equals(data.get(0).getRfc()));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getCurp())));
						Assert.assertTrue("MXN".equals(data.get(0).getDivisa()));
						Assert.assertTrue("2222.00".equals(data.get(0).getImporte()));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getIva())));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getComision())));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getIvaComision())));
						Assert.assertTrue("PAGO, CUENTA, TERCERO, CUARENTA123".equals(data.get(0).getConcepto()));
						Assert.assertTrue("7777777".equals(data.get(0).getReferencia()));
						Assert.assertTrue("MXTEST@BANCOSABADELL.MX".equals(data.get(0).getCorreoElectronico()));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getNumeroCelular())));						
						Assert.assertTrue("201808081122334455".equals(data.get(0).getClaveRastreo()));
						Assert.assertTrue("001235680045780564585".equals(data.get(0).getFolioOperacion()));
						Assert.assertTrue("PCM60001".equals(data.get(0).getUsuario()));
						Assert.assertTrue("LIQUIDADO".equals(data.get(0).getEstadoOperacion()));
						Assert.assertTrue("CORPORATIVO DE INFORMATICA".equals(data.get(0).getCliente()));
						Assert.assertTrue("2B2D3B43CD3F29637E7A02B03B343434".equals(data.get(0).getHash()));
						Assert.assertTrue(data.get(0).isValidChecksum());
					}
				});
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void ImportDispersionDefinitivaTXTTest() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layouts/20180808_11111111111_444_D.txt").getFile());
		final DispersionDefinitivaTXTImporter importer = new DispersionDefinitivaTXTImporter(
				new ImportTarget<DispersionDefinitiva>() {
					@Override
					public void setData(List<DispersionDefinitiva> data) {
						Assert.assertNotNull(data);
						Assert.assertTrue(data.size() == 12);						
						Assert.assertTrue("0".equals(data.get(0).getTipoMovimiento()));
						Assert.assertTrue("H".equals(data.get(0).getAplicacion()));
						Assert.assertTrue("20180808114713".equals(data.get(0).getFecha()));
						Assert.assertTrue("00".equals(data.get(0).getTipoTransaccion()));
						Assert.assertTrue("00000212004".equals(data.get(0).getCuentaCargo()));
						Assert.assertTrue("01".equals(data.get(0).getTipoCuentaBeneficiario()));
						Assert.assertTrue("00000129001".equals(data.get(0).getCuentaAbono()));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getBanco())));						
						Assert.assertTrue("PF".equals(data.get(0).getTipoPersona()));
						Assert.assertTrue("OSCAR YAIR SANCHEZ SALAZAR".equals(data.get(0).getNombre()));
						Assert.assertTrue("MAJO760113T85".equals(data.get(0).getRfc()));
						Assert.assertTrue("MAJO760114MNERSL01".equals((data.get(0).getCurp())));
						Assert.assertTrue("MXN".equals(data.get(0).getDivisa()));
						Assert.assertTrue("25000.10".equals(data.get(0).getImporte()));
						Assert.assertTrue("1.16".equals((data.get(0).getIva())));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getComision())));
						Assert.assertTrue(StringUtils.isBlank((data.get(0).getIvaComision())));
						Assert.assertTrue("PAGO A CUENTA TERCERO".equals(data.get(0).getConcepto()));
						Assert.assertTrue("7654321".equals(data.get(0).getReferencia()));
						Assert.assertTrue("MXTEST01@BANCOSABADELL.MX".equals(data.get(0).getCorreoElectronico()));
						Assert.assertTrue("5545748551".equals((data.get(0).getNumeroCelular())));						
						Assert.assertTrue("201808089988775566".equals(data.get(0).getClaveRastreo()));
						Assert.assertTrue("000265896589874455880".equals(data.get(0).getFolioOperacion()));
						Assert.assertTrue("PCM60001".equals(data.get(0).getUsuario()));
						Assert.assertTrue("LIQUIDADO".equals(data.get(0).getEstadoOperacion()));
						Assert.assertTrue("EMPRESA IMPORTADORA DE TEXTIL".equals(data.get(0).getCliente()));
						Assert.assertTrue("050F6DACBFA082B4CF61FB385CD29C7D".equals(data.get(0).getHash()));
						Assert.assertTrue(data.get(0).isValidChecksum());
					}
				});
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} 
	}
	
	@Test
	public void validatorImportCSVTest() {		
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layouts/20180808_11111111111_222_D.csv").getFile());
		final DispersionDefinitivaValidator validator = new DispersionDefinitivaValidator(); 
		final DispersionDefinitivaCSVImporter importer = new DispersionDefinitivaCSVImporter(
				new ImportTarget<DispersionDefinitiva>() {

					@Override
					public void setData(List<DispersionDefinitiva> data) {
						Assert.assertTrue(validator.isValid(data));
					}
				});
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void validadorImportTXTTest() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layouts/20180808_11111111111_444_D.txt").getFile());
		final DispersionDefinitivaValidator validator = new DispersionDefinitivaValidator();
		final DispersionDefinitivaTXTImporter importer = new DispersionDefinitivaTXTImporter(
				new ImportTarget<DispersionDefinitiva>() {
					@Override
					public void setData(List<DispersionDefinitiva> data) {
						Assert.assertTrue(validator.isValid(data));
					}
				});
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} 
	}
}
