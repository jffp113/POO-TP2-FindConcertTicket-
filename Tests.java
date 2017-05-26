import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;



public class Tests {


	private static final String FORMATO_INPUT = "input_$.txt";
	private static final String FORMATO_OUTPUT = "output_$.txt";  


	@Test public void test1() { test("01-in-register_loginout_ok.txt","01-out-register_loginout_ok.txt"); }
	@Test public void test2() { test("01-in-register_loginout_pre.txt","01-out-register_loginout_pre.txt"); }
	@Test public void test3() { test("02-in-addartistshow_shows_ok.txt","02-out-addartistshow_shows_ok.txt"); }
	@Test public void test4() { test("02-in-addartistshow_shows_pre.txt","02-out-addartistshow_shows_pre.txt"); }
	@Test public void test5() { test("03-in-showsbytype_search_ok.txt","03-out-showsbytype_search_ok.txt"); }
	@Test public void test6() { test("03-in-showsbytype_search_pre.txt","03-out-showsbytype_search_pre.txt"); }
	@Test public void test7() { test("04-in-buymytickets_ok.txt","04-out-buymytickets_ok.txt"); }
	@Test public void test8() { test("04-in-buymytickets_pre.txt","04-out-buymytickets_pre.txt"); }
	@Test public void test9() { test("05-in-everything_ok.txt","05-out-everything_ok.txt"); }
	@Test public void test10() { test("05-in-everything_pre.txt","05-out-everything_pre.txt"); }
	@Test public void EnunciadoTest() { test("in-enunciado.txt","out-enunciado.txt"); }
	
	


	private static final File BASE = new File("tests");

	private PrintStream consoleStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		consoleStream = System.out;
		System.setOut(new PrintStream(outContent));
	}

	public void test(int num) {
		String in = FORMATO_INPUT.replaceAll("\\$", Integer.toString(num));
		String out = FORMATO_OUTPUT.replaceAll("\\$", Integer.toString(num)); 
		test(in, out);
	}

	public void test(String intput, String output) {
		test(new File(BASE, intput), new File(BASE, output));
	}

	public void test(File input, File output) {
		consoleStream.println("Testing!");
		consoleStream.println("Input: " + input.getAbsolutePath());
		consoleStream.println("Output: " + output.getAbsolutePath());

		String fullInput = "", fullOutput = "";
		try {
			fullInput = new String(Files.readAllBytes(input.toPath()));
			fullOutput = new String(Files.readAllBytes(output.toPath()));
			consoleStream.println("INPUT ============");
			consoleStream.println(new String(fullInput));
			consoleStream.println("OUTPUT ESPERADO =============");
			consoleStream.println(new String(fullOutput));
			consoleStream.println("OUTPUT =============");
		} catch(Exception e) {
			e.printStackTrace();
			fail("Erro a ler o ficheiro");
		}

		try {
			Locale.setDefault(Locale.US);
			System.setIn(new FileInputStream(input));
			Class<?> mainClass = Class.forName("Main");
			mainClass.getMethod("main", String[].class).invoke(null, new Object[] { new String[0] });
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro no programa");
		} finally {
			byte[] outPrintBytes = outContent.toByteArray();
			consoleStream.println(new String(outPrintBytes));

			assertEquals(removeCarriages(fullOutput), removeCarriages(new String(outContent.toByteArray())));
		}
	}

	private static String removeCarriages(String s) {
		return s.replaceAll("\r\n", "\n");
	}

}
