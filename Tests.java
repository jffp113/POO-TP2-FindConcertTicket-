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

import static org.junit.Assert.*;
import org.junit.Test;


public class Tests {

	/* 
	 * #########################################################################################################
	 * FORMATO DOS FICHEIROS
	 *
	 * O $ e o numero do teste. O valor normal serve para testes do estilo
	 * "input_1.txt" -> "output_1.txt", "input_4.txt" -> "output_4.txt", etc
	 * Troquem os strings com o formato correto dos testes que sao fornecidos
	 * No caso de haver pastas, usem separadores /
	 * Ex. Se os testes forem T1/in.txt e T1/out.txt, o formato seria, respetivamente,
	 * "T$/in.txt", "T$/out.txt"
	 *
	 * NOTA: Estes valores nao vem configurados para nenhum problema em particular
	 * e devem ter de ser mudados.
	 * #########################################################################################################
	 */

	private static final String FORMATO_INPUT = "input_$.txt";
	private static final String FORMATO_OUTPUT = "output_$.txt";  

	/* #########################################################################################################
	 * LISTAGEM DOS TESTES
	 *
	 * Os testes sao escritos no formato ja presente, com os numeros que vem nos zips dados
	 * O formato que vem so tem os testes 1, 2, e 3. Adicionar mais testes e so continuar
	 * a lista com os valores seguintes. Exemplo, adicionar os testes 4 e 5:
	 * @Test public void test4() { test(4); }
	 * @Test public void test5() { test(5); }
	 * Mudar:                ^          ^
	 * #########################################################################################################
	 */

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
	
	
	
	/*
	 * No caso dos testes terem formatos irregulares (nao serem com numeros corridos)
	 * temos de dar manualmente os nomes dos testes, tipo assim:
	 * @Test public void testMalo() { test("malo_input.txt", "malo_output.txt"); }
	 */
	
	/*
	 * #########################################################################################################
	 * SE TOCAREM EM ALGUMA COISA ABAIXO DISTO CORREM O RISCO DE EXPLODIR COM TUDO
	 * #########################################################################################################
	 */

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
