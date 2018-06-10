
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author HP
 */
public class GestionarPonderablesIT {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.katalon.com/";
    }

    @Test
    public void verPonderables() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/datosProcesados.xhtml");
        driver.findElement(By.id("j_idt35:j_idt45")).click();
        WebElement recuperados = driver.findElement(By.id("formulario:j_idt39"));
        String texto = recuperados.getText();
        Assert.assertEquals(texto, "Listado de Aspirantes por Programa que se pueden ponderar.");
    }
    @Test
    public void listarPonderables() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasPonderables.xhtml");
        WebElement recuperados = driver.findElement(By.id("formulario:recuperados"));
        String texto = recuperados.getText();
        Assert.assertEquals(texto, "Mostrando 7 de 7 programas");
    }
    @Test
    public void listarPonderablesPrograma() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasPonderables.xhtml");
        driver.findElement(By.xpath("//button[@id='formulario:tablaMostrarProg:0:j_idt54']/span")).click();
        WebElement recuperados = driver.findElement(By.id("formulario:recuperados"));
        String texto = recuperados.getText();
        Assert.assertEquals(texto, "Mostrando 29 de 29 aspirantes ponderables");
    }
    @Test
    public void filtrarPorFacultad() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasPonderables.xhtml");
        driver.findElement(By.id("formulario:tablaMostrarProg:j_idt49:filter")).click();
        driver.findElement(By.id("formulario:tablaMostrarProg:j_idt49:filter")).clear();
        driver.findElement(By.id("formulario:tablaMostrarProg:j_idt49:filter")).sendKeys("Sistemas");
        WebElement datatable = driver.findElement(By.id("formulario:tablaMostrarProg"));
        String texto = datatable.getText();
         if(!texto.contains("Sistemas")) {
            fail("No funciona el buscar");
        }
    }
    @Test
    public void filtrar() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasPonderables.xhtml");
        driver.findElement(By.xpath("//div[@id='formulario:console']/div[3]/span")).click();
        driver.findElement(By.xpath("//div[@id='formulario:console_panel']/div/ul/li[2]")).click();
        driver.findElement(By.xpath("//button[@id='formulario:j_idt45']/span")).click();
        WebElement datatable = driver.findElement(By.id("formulario:tablaMostrarProg"));
        String texto = datatable.getText();
        if(texto.replaceAll("Ver\n", "").split("\n").length != 6) {
            fail("No funciona el filtrar");
        }                
    }
    @Test
    public void buscarId() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesPorPrograma.xhtml");
        driver.findElement(By.id("formulario:tablaListPond:j_idt42:filter")).click();
        driver.findElement(By.id("formulario:tablaListPond:j_idt42:filter")).clear();
        driver.findElement(By.id("formulario:tablaListPond:j_idt42:filter")).sendKeys("8000");
        WebElement datatable = driver.findElement(By.id("formulario:tablaListPond"));
        String texto = datatable.getText();
        if (!texto.contains("8000")) {
            fail("No funciona el buscar por id");
        }
    }
    @Test
    public void buscarNombre() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesPorPrograma.xhtml");
        driver.findElement(By.id("formulario:tablaListPond:j_idt44:filter")).click();
        driver.findElement(By.id("formulario:tablaListPond:j_idt44:filter")).clear();
        driver.findElement(By.id("formulario:tablaListPond:j_idt44:filter")).sendKeys("Daria");
        WebElement datatable = driver.findElement(By.id("formulario:tablaListPond"));
        String texto = datatable.getText();
        System.out.println(texto);
        if (!texto.contains("Daria")) {
            fail("No funciona el buscar por nombre");
        }
    }

    @Test
    public void mostrarInfoAspirantePonderable() throws Exception {
        driver.get("http://localhost:8080/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesPorPrograma.xhtml");
        driver.findElement(By.xpath("//button[@id='formulario:tablaListPond:0:j_idt47']/span")).click();
        WebElement titulo = driver.findElement(By.id("titulo"));
        String texto = titulo.getText();
        Assert.assertEquals(texto, "Mostrando la información del aspirante ponderable");        
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
